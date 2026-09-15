package Garaza.Model;

import jakarta.persistence.*;

@Entity
@Table(name="Automobil")
public class Automobil  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String marka;
    String model;
    String registracija;


    public Automobil( String marka, String model, String registracija){
        this.marka=marka;
        this.model=model;
        this.registracija=registracija;

    }
    protected Automobil(){}

    public int getId(){
        return this.id;
    }
    public String getMarka() {
        return marka;
    }
    public String getModel() {
        return model;
    }
    public String getRegistracija() {
        return registracija;
    }

    public void setMarka(String marka) {this.marka = marka;}
    public void setModel(String model) {this.model = model;}
    public void setRegistracija(String registracija) {this.registracija = registracija;}
}
