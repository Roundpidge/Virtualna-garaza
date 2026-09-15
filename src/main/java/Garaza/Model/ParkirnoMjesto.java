package Garaza.Model;

import Garaza.interfaces.PrikaziInfo;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="ParkirnoMjesto")
public class ParkirnoMjesto implements PrikaziInfo {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   int p_id;
   String kodMjesta;
   Boolean parkiran;
   LocalDateTime timestamp;

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "a_id")
   private Automobil automobil;

   public ParkirnoMjesto(int brojMjesta){
      this.parkiran=false;
            if(brojMjesta>=1 && brojMjesta<=24){ this.kodMjesta="A"+brojMjesta; }
            else if(brojMjesta>=25 && brojMjesta<=37){ this.kodMjesta="B"+brojMjesta; }
           else  if(brojMjesta>=38 && brojMjesta<=50){ this.kodMjesta="C"+brojMjesta; }
           else  if(brojMjesta>=51 && brojMjesta<=63){ this.kodMjesta="D"+brojMjesta; }
          else  if(brojMjesta>=64 && brojMjesta<=77){ this.kodMjesta="E"+brojMjesta; }
           else if(brojMjesta>=78 && brojMjesta<=150){ this.kodMjesta="F"+brojMjesta; }
           else{
              throw new IllegalArgumentException("Parkirno mjesto mora biti između 1 i 150");
            }
   }
   protected ParkirnoMjesto(){}

   @Override
   public void prikaziInfo() {
      System.out.println("Marka: "+ automobil.getMarka());
      System.out.println("Model: " + automobil.getModel());
      System.out.println( "Registracija: "+automobil.getRegistracija());
      System.out.println( "Registracija: "+automobil.getRegistracija());
   }

    public int getP_id() {return p_id;}
    public Boolean getParkiran() {return parkiran;}
    public String getKodMjesta() {return kodMjesta;}
    public LocalDateTime getTimestamp() {return timestamp;}
    public Automobil getAutomobil() {return automobil;}

    public void setKodMjesta(String kodMjesta) {this.kodMjesta = kodMjesta;}
    public void setParkiran() {this.parkiran = true;}
    public void setIsparkiran() {this.parkiran = false;}
    public void setTimestamp(LocalDateTime timestamp) {this.timestamp = timestamp;}
    public void setAutomobil(Automobil automobil) {this.automobil = automobil;}
}




