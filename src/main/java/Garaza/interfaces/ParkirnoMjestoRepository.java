package Garaza.interfaces;
import Garaza.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface ParkirnoMjestoRepository extends JpaRepository<ParkirnoMjesto, Integer> {
    Optional<ParkirnoMjesto> findByKodMjesta(String kodMjesta);
}
