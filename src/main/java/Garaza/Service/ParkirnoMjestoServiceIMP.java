package Garaza.Service;

import Garaza.Model.*;
import Garaza.interfaces.ParkirnoMjestoRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ParkirnoMjestoServiceIMP implements ParkirnoMjestoService {

    private final ParkirnoMjestoRepository repo;

    public ParkirnoMjestoServiceIMP(ParkirnoMjestoRepository repo) {
        this.repo = repo;
    }


    @Override
    public ParkirnoMjesto saveMjesto(ParkirnoMjesto mjesto) {
        return repo.save(mjesto);
    }


    // CREATE
    @Override
    public ParkirnoMjesto parkirajAutomobil(
            int p_id,
            ParkirnoMjesto mjesto,
            Automobil automobil) {

        mjesto.setParkiran();
        mjesto.setAutomobil(automobil);
        mjesto.setTimestamp(LocalDateTime.now());

        return repo.save(mjesto);
    }


    // READ SVI
    @Override
    public List<ParkirnoMjesto> fetchParkirnaMjesta() {
        return repo.findAll();
    }


    // READ PO KODU
    @Override
    public Optional<ParkirnoMjesto> findByKodMjesta(String kodMjesta) {
        return repo.findByKodMjesta(kodMjesta);
    }


    // UPDATE
    @Override
    public ParkirnoMjesto ispravakPodataka(
            String kodMjesta,
            Automobil noviPodaci) {

        ParkirnoMjesto mjesto =
                repo.findByKodMjesta(kodMjesta).orElseThrow();

        Automobil automobil = mjesto.getAutomobil();

        automobil.setMarka(noviPodaci.getMarka());
        automobil.setModel(noviPodaci.getModel());
        automobil.setRegistracija(noviPodaci.getRegistracija());

        return repo.save(mjesto);
    }


    // DELETE / ISPARKIRAVANJE
    @Override
    public void isparkirajAutomobil(int p_id) {

        ParkirnoMjesto mjesto = repo.findById(p_id).orElseThrow();
        mjesto.setAutomobil(null);
        mjesto.setIsparkiran();
        mjesto.setTimestamp(null);

        repo.save(mjesto);
    }
}