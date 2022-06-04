package model;


import javax.persistence.*;

@Entity
@Table(name = "colete")
public class Colet {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @Column
    private String dest;

    @Column
    private Integer greutate;

    @Column
    private Double x;

    @Column
    private Double y;

    @Column
    private String numePostas;

    public Colet() { }
    public Colet(String dest, int greutate, double x, double y, String numePostas) {
        this.dest = dest;
        this.greutate = greutate;
        this.x=x;
        this.y=y;
        this.numePostas=numePostas;
    }



    public String getDest() { return dest; }
    public void setDest(String dest) { this.dest = dest; }
    public int getGreutate() {
        return greutate;
    }
    public void setGreutate(int greutate) {
        this.greutate = greutate;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id; }
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public String getNumePostas() { return numePostas; }
    public void setNumePostas(String numePostas) { this.numePostas = numePostas; }
}
