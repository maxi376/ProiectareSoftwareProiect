package dataAcces;

public class DTOAdministrator extends DTOAngajat{
    //private int functie=1;

    public static int selected=-1;


    public DTOAdministrator() { super.setFunctie(1); }
    public DTOAdministrator(String numeUtilizator, String parola) {
        super(numeUtilizator, parola);
        super.setFunctie(1);
    }
    public DTOAdministrator(DTOAngajat dtoAngajat) {
        super(dtoAngajat);
        super.setFunctie(2);
    }

    //


    public String toString(){
        return this.getNumeUtilizator()+" "+this.getParola();
    }
}
