package model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "postas")
public class Postas extends Angajat{

    public Postas() { super.setFunctie(3);}
    public Postas(String numeUtilizator, String parola) {
        super(numeUtilizator,parola);
        super.setFunctie(3);
    }
    public Postas(Integer id,String numeUtilizator, String parola) {
        super(id,numeUtilizator,parola);
        super.setFunctie(3);
    }
}
