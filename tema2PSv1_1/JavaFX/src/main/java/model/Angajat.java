package model;

import javax.persistence.*;

@MappedSuperclass
public abstract class Angajat {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @Column
    private String numeUtilizator;

    @Column
    private String parola;

    @Transient
    private Integer functie=0;

    @Transient
    public static Integer selectedAngajat=-1;



    public Angajat() { }
    public Angajat(String numeUtilizator, String parola) {
        this.numeUtilizator = numeUtilizator;
        this.parola = parola;
    }
    public Angajat(Integer id,String numeUtilizator, String parola) {
        this.id=id;
        this.numeUtilizator = numeUtilizator;
        this.parola = parola;
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
        Angajat.selectedAngajat = selectedAngajat;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
}
