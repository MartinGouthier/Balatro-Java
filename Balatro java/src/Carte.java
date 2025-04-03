public abstract class Carte {
    private boolean estSelectionnee;
    private int id;
    public static int nbrCartesTotal = 0;

    public Carte(){
        this.estSelectionnee = false;
        this.id = nbrCartesTotal;
        nbrCartesTotal++;

    }



    public boolean getEstSelectionnee() {
        return estSelectionnee;
    }

    public void prendreCarte() {
        estSelectionnee = !estSelectionnee;
    }

    public abstract String toString(boolean b);

    public boolean equals(Object o){
        Carte c = (Carte) o;
        return (this.id == c.id);
    }
}