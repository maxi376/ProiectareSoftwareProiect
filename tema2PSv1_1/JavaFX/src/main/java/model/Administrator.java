package model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "administrator")
public class Administrator extends Angajat{
    //private int functie=1;

    @Transient
    public static int selected=-1;



    public Administrator() { super.setFunctie(1); }
    public Administrator(String numeUtilizator, String parola) {
        super(numeUtilizator, parola);
        super.setFunctie(1);
    }
    public Administrator(Integer id,String numeUtilizator, String parola) {
        super(id,numeUtilizator, parola);
        super.setFunctie(1);
    }
}
