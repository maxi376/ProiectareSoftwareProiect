package dataAcces;


public abstract class DTOAngajat {
    private Integer id;

    private String numeUtilizator;

    private String parola;

    private Integer functie=0;

    public static Integer selectedAngajat=-1;



    public DTOAngajat() {
    }
    public DTOAngajat(String numeUtilizator, String parola) {
        this.numeUtilizator = numeUtilizator;
        this.parola = parola;
    }
    public DTOAngajat(DTOAngajat angajat) {
        numeUtilizator=angajat.getNumeUtilizator();
        parola=angajat.getParola();
    }



    public String getNumeUtilizator() {
        return numeUtilizator;
    }
    public void setParola(String parola){
        this.parola=parola;
    }
    public void setNumeUtilizator(String numeUtilizator) {
        this.numeUtilizator = numeUtilizator;
    }
    public String getParola() {
        return parola;
    }
    public int getFunctie() { return functie; }
    public void setFunctie(int functie) { this.functie = functie; }
    public static int getSelectedAngajat() {
        return selectedAngajat;
    }
    public static void setSelectedAngajat(int selectedAngajat) {
        DTOAngajat.selectedAngajat = selectedAngajat;
    }
    //public PersistentaColetelor getColetelor() { return null;}
    //public void setColetelor(PersistentaColetelor coletelor) { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
