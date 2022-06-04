package dataAcces;

public class DTOCoordonatorActivitate extends DTOAngajat{

    public static int selected=-1;


    public DTOCoordonatorActivitate(){super.setFunctie(2);}
    public DTOCoordonatorActivitate(String numeUtilizator, String parola) {
        super(numeUtilizator,parola);
        super.setFunctie(2);
    }
    public DTOCoordonatorActivitate(DTOAngajat dtoAngajat) {
        super(dtoAngajat);
        super.setFunctie(2);
    }

    //

    public String toString(){
        return this.getNumeUtilizator()+" "+this.getParola();
    }
}
