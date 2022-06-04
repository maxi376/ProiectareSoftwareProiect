package model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "coordonatoractivitate")
public class CoordonatorActivitate extends Angajat{
    //private int functie=2;

    @Transient
    public static int selected=-1;


    public CoordonatorActivitate(){super.setFunctie(2);}
    public CoordonatorActivitate(String numeUtilizator, String parola) {
        super(numeUtilizator,parola);
        super.setFunctie(2);
    }
    public CoordonatorActivitate(Integer id,String numeUtilizator, String parola) {
        super(id,numeUtilizator,parola);
        super.setFunctie(2);
    }

}
