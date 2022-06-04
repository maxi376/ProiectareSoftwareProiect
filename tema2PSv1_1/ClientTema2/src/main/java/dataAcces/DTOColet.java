package dataAcces;


public class DTOColet {

    private String dest;

    private Integer greutate;

    private Integer id;

    private Double x;

    private Double y;

    private String numePostas;

    public DTOColet() { }
    public DTOColet(String dest, int greutate, double x, double y,String numePostas) {
        this.dest = dest;
        this.greutate = greutate;
        this.x=x;
        this.y=y;
        this.numePostas=numePostas;
    }
    public DTOColet(String dest, int greutate, Integer id, double x, double y,String numePostas) {
        this.dest = dest;
        this.greutate = greutate;
        this.id=id;
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
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) { this.id = id; }
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public String getNumePostas() { return numePostas; }
    public void setNumePostas(String numePostas) { this.numePostas = numePostas; }

    public String toString(){
        return dest+" "+greutate+" "+x+" "+y+" "+numePostas;
    }
}