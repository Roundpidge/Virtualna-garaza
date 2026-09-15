package Garaza.Service;
import Garaza.Model.*;
import java.util.*;

public interface ParkirnoMjestoService {
    // Save operation
    ParkirnoMjesto saveMjesto(ParkirnoMjesto mjesto);

    // CREATE
    ParkirnoMjesto parkirajAutomobil(int p_id, ParkirnoMjesto mjesto,  Automobil automobil);

    // READ SVI AUTI
    List<ParkirnoMjesto> fetchParkirnaMjesta();

    //READ POJEDINAČNA MJESTA
    Optional<ParkirnoMjesto> findByKodMjesta(String kodMjesta);


    // UPDATE
    ParkirnoMjesto ispravakPodataka(String kodMjesta, Automobil noviPodaci);

    // DELETE
    void isparkirajAutomobil(int p_id);
}
