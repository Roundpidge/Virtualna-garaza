package Garaza.Controller;

import Garaza.Form.ParkirnoMjestoForm;
import Garaza.Model.*;
import Garaza.Service.ParkirnoMjestoService;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequestMapping("/garaza")
public class ParkirnoMjestoController {

    private final ParkirnoMjestoService service;


    public ParkirnoMjestoController(ParkirnoMjestoService service) {
        this.service = service;
    }

//PRIKAZI FORM
    @GetMapping("/parkiraj")
    public String prikazi(Model model) {

        model.addAttribute(
                "ParkirnoMjestoForm",
                new ParkirnoMjestoForm()
        );

        return "parkirnaMjesta/form";
    }

    //CREATE
    @PostMapping("/parkiraj")
    public String upis(
            @Valid @ModelAttribute("ParkirnoMjestoForm") ParkirnoMjestoForm form, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        System.out.println("REGISTRACIJA: " + form.getRegistracija());
        System.out.println("IMA GREŠAKA: " + result.hasErrors());
        System.out.println("GREŠKE: " + result.getAllErrors());

        if (result.hasErrors()) {
            return "parkirnaMjesta/form";
        }

        Optional<ParkirnoMjesto> mjesto =
                service.findByKodMjesta(form.getKodMjesta());

        if (mjesto.isEmpty()) {
            model.addAttribute("message", "Parkirno mjesto ne postoji.");
            return "parkirnaMjesta/form";
        }

        ParkirnoMjesto parkirnoMjesto = mjesto.get();

        // MJESTO JE VEĆ ZAUZETO
        if (parkirnoMjesto.getParkiran()) {
            model.addAttribute("message", "Parkirno mjesto " + form.getKodMjesta() + " je već zauzeto.");
            return "parkirnaMjesta/form";
        }

        Automobil automobil = new Automobil(
                form.getMarka(),
                form.getModel(),
                form.getRegistracija()
        );

        service.parkirajAutomobil(
                parkirnoMjesto.getP_id(),
                parkirnoMjesto,
                automobil
        );

        redirectAttributes.addFlashAttribute(
                "message",
                "Automobil je uspješno parkiran."
        );

        return "redirect:/garaza";
    }



    // READ
    @GetMapping({"", "/{kodMjesta}"})
    public String read(@PathVariable(required = false) String kodMjesta, Model model) {

        // SVI AUTOMOBILI
        if (kodMjesta == null) {

            List<ParkirnoMjesto> parkirnaMjesta = service.fetchParkirnaMjesta();

            int kapacitet = 150;
            int zauzeto = 0;

            for (ParkirnoMjesto mjesto : parkirnaMjesta) {
                if (mjesto.getParkiran()) {
                    zauzeto++;
                }
            }

            int slobodno = kapacitet - zauzeto;

            model.addAttribute("parkirnaMjesta", parkirnaMjesta);
            model.addAttribute("kapacitet", kapacitet);
            model.addAttribute("zauzeto", zauzeto);
            model.addAttribute("slobodno", slobodno);

            return "index";
        }


        // READ ONE
        Optional<ParkirnoMjesto> mjesto = service.findByKodMjesta(kodMjesta);

        if (mjesto.isPresent()) {
            model.addAttribute("mjesto", mjesto.get());
            return "parkirnaMjesta/details";
        }

        return "redirect:/garaza";
    }

    // DELETE
    @PostMapping("/{p_id}/delete")
    public String delete(@PathVariable int p_id, RedirectAttributes redirectAttributes) {
        service.isparkirajAutomobil(p_id);
        //POTVRDA USPJEŠNOG BRISANJA AUTOMOBILA
        redirectAttributes.addFlashAttribute("message", "Automobil je uspješno obrisan.");
        return "redirect:/garaza";
    }

    //PRETRAGA PO KODU MJESTA
    @GetMapping("/pretraga")
    public String pretraga(@RequestParam String kodMjesta, Model model) {
        Optional<ParkirnoMjesto> mjesto = service.findByKodMjesta(kodMjesta);
        model.addAttribute("mjesto", mjesto.orElse(null));
        return "parkirnaMjesta/pretraga";
    }

    @PostMapping("/uredi")
    public String azuriraj(
            @Valid @ModelAttribute("ParkirnoMjestoForm") ParkirnoMjestoForm form,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "parkirnaMjesta/formUpdate";
        }

        Automobil automobil = new Automobil(
                form.getMarka(),
                form.getModel(),
                form.getRegistracija()
        );

        service.ispravakPodataka(form.getKodMjesta(), automobil);

        redirectAttributes.addFlashAttribute(
                "message",
                "Podaci su uspješno ažurirani."
        );

        return "redirect:/garaza";
    }


    // UPDATE, ALI SLUŽI DA OTVORI FORMU ZA AŽURIRANJE PODATAKA
    @GetMapping("/uredi/{kod}")
    public String uredi(
            @PathVariable String kod, Model model) {

        Optional<ParkirnoMjesto> mjesto = service.findByKodMjesta(kod);

        if (mjesto.isPresent()) {

            ParkirnoMjesto parkirnoMjesto = mjesto.get();

            ParkirnoMjestoForm form = new ParkirnoMjestoForm();

            form.setKodMjesta(parkirnoMjesto.getKodMjesta());
            form.setMarka(parkirnoMjesto.getAutomobil().getMarka());
            form.setModel(parkirnoMjesto.getAutomobil().getModel());
            form.setRegistracija(parkirnoMjesto.getAutomobil().getRegistracija());

            model.addAttribute("ParkirnoMjestoForm", form);
        }

        return "parkirnaMjesta/formUpdate";
    }

}