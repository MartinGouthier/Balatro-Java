public abstract class AutreCarte extends Carte {
    private int prixVente;
    private int negatif;

    public int getPrixVente() {
        return prixVente;
    }

    public int getNegatif() {
        return negatif;
    }

    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }

    public void setNegatif(int negatif) {
        this.negatif = negatif;
    }

    public AutreCarte(int prix, int neg){
        super();
        this.prixVente = prix;
        this.negatif = neg;
    }
}
