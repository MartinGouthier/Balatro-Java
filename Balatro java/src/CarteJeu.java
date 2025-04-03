import java.util.Random;

public class CarteJeu extends Carte {
    private int couleur;
    private int valeur;
    private final int idCarte;
    private int chips;

    public static int nbrCarte = 1;

    private int edition;
    /**
     * 1 : Seau rouge
     * 2 : Seau doré
     * 3 : Seau violet
     * 4 : Seau bleu
     */
    private int seau;
    private int amelioration;

    /**
     * Constructeur carte de jeu avec veleur et couleur
     * @param valeur Valeur de la carte
     * @param couleur Couleur de la carte
     */
    public CarteJeu(int valeur, int couleur) {
        super();
        this.couleur = couleur;
        this.valeur = valeur;
        this.idCarte = nbrCarte;
        this.updateChips();

        nbrCarte++;
    }

    public CarteJeu(){
        Random rand = new Random();
        this.valeur = rand.nextInt(13)+1;
        this.couleur = rand.nextInt(4)+1;
        this.idCarte = nbrCarte;
        this.updateChips();

        nbrCarte++;

    }

    public int getCouleur() {
        return couleur;
    }
    public int getValeur() {
        return valeur;
    }
    public int getIdCarte() {
        return idCarte;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }
    public void setValeur(int valeur) {
        this.valeur = valeur;
        this.updateChips();
    }
    public void incrementeValeur(int valeur){
        this.valeur += valeur;
        if (this.valeur > 13)
            this.valeur = this.valeur - 13;
        this.updateChips();
    }

    public void updateChips(){
        this.chips = valeur;
        if (valeur > 10)
            this.chips = 10;
        if (valeur == 1)
            this.chips = 11;
    }

    public String toString(boolean reduit){
        StringBuilder str = new StringBuilder();
        if ((this.valeur < 11)&&(this.valeur>1))
            str.append(this.valeur).append(" de ");
        else
            switch(this.valeur){
            case 1:
                str.append("As de ");
                break;
            case 11:
                str.append("Valet de ");
                break;
            case 12:
                str.append("Dame de ");
                break;
            case 13:
                str.append("Roi de ");
        }
        switch (this.couleur){
            case 1:
                str.append("pique");
                break;
            case 2:
                str.append("trefle");
                break;
            case 3:
                str.append("coeur");
                break;
            case 4:
                str.append("carreau");
                break;
        }
        if (!reduit) {
            str.append(" (").append(this.chips).append(" jetons)");
            if (this.getEstSelectionnee())
                str.append(" Sélectionné");
        }
        return str.toString();

    }

    public Score marquerPoints(Score score){
        int n = this.chips;
        if (this.seau == 2)
            Jeu.argent += 3;
        switch (this.amelioration){
            case 1:
                n += 30;
                break;
            case 2:
                score.ajouterMult(4);
                break;
            case 3:
                n = 50;
                break;
            case 4:
                if (Jeu.calculerProba(1,5))
                    score.ajouterMult(20);
                if (Jeu.calculerProba(1,15))
                    Jeu.argent += 20;
                break;
            case 5:
                score.multiplierMult(2);
                if (Jeu.calculerProba(1,4))
                    //TODO CASSER CARTE
                break;

        }
        score.ajouterChips(n);
        switch (this.edition) {
            case 1:
                score.ajouterChips(50);
                break;
            case 2:
                score.ajouterMult(10);
                break;
            case 3:
                score.multiplierMult(1.5);
                break;
        }
        return score;
    }

    public Score jouer(Score s){
        s = this.marquerPoints(s);
        if (this.seau == 1)
            s = marquerPoints(s);
        return s;
    }

    public static void main(String[] args) {
        int i = Integer.parseInt(args[0]);
    }



}
