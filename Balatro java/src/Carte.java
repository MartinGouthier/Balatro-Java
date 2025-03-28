public abstract class Carte {
    private boolean estSelectionnee;

    public Carte(){
        this.estSelectionnee = false;
    }



    public boolean getEstSelectionnee() {
        return estSelectionnee;
    }

    public void setEstSelectionnee() {
        estSelectionnee = !estSelectionnee;
    }

    public abstract String toString(boolean b);
}