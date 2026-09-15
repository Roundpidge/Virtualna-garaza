package Garaza.Form;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class ParkirnoMjestoForm {
    @NotBlank(message = "Ime makre je obavezna")
    private String marka;
    @NotBlank(message = "Ime modela je obavezna")
    private String model;
    @Pattern(regexp = "^[A-ZČĆĐŠŽ]{2}[ -]?\\d{3,4}[ -]?[A-ZČĆĐŠŽ]{1,2}$", message = "Registracija mora biti oblika npr. PU-123-AB ili HR 8004-EU.")
    private String registracija;
    @NotBlank(message = "Odaberite parkirno mjesto od 1A do 150E")
    private String kodMjesta;

    LocalDateTime timestamp;
    Boolean parkiran;

    public String getMarka() {return marka;}
    public String getModel() {return model;}
    public String getKodMjesta() {return kodMjesta;}
    public String getRegistracija() {return registracija;}
    public LocalDateTime getTimestamp() {return timestamp;}
    public Boolean getParkiran() {return parkiran;}

    public void setMarka(String marka) {this.marka = marka;}
    public void setModel(String model) {this.model = model;}
    public void setKodMjesta(String kodMjesta) {this.kodMjesta = kodMjesta;}
    public void setRegistracija(String registracija) {this.registracija = registracija;}
    public void setTimestamp(LocalDateTime timestamp) {this.timestamp = timestamp;}
    public void setParkiran(Boolean parkiran) {this.parkiran = parkiran;}
}
