package Garaza.Model;

import Garaza.interfaces.PrikaziInfo;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name="Garaza")
public class Garaza implements PrikaziInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int g_id;
    int kapacitet=150;
    protected Garaza(){}

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "garaza_id")
    private List<ParkirnoMjesto> mjesta = new ArrayList<>();

    @Override
    public void prikaziInfo(){
            System.out.println("PARKING INFO: "+ " Kapacitet garaže: "+ kapacitet );
    }

    public int getKapacitet() {return kapacitet;}

    public int setKapacitetPOZITIVNO() {return kapacitet ++;}
    public int setKapacitetNEGATIVNO() {return kapacitet --;}
}
