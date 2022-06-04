package dataAcces;

public class DTOPostas extends DTOAngajat{

    public static int selected=-1;


    public DTOPostas() { super.setFunctie(3);}
    public DTOPostas(String numeUtilizator, String parola) {
        super(numeUtilizator,parola);
        super.setFunctie(3);
    }
    public DTOPostas (DTOAngajat dtoAngajat) {
        super(dtoAngajat);
        super.setFunctie(2);
    }

    //

    public String toString(){
        return this.getId()+" "+this.getNumeUtilizator()+" "+this.getParola();
    }
}
