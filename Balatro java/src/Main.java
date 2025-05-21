import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main extends Deck {

    /**
     * Taille maximale de la main
     */
    public static int tailleMain = 8;
    /**
     * Jetons et Mult actuels
     */
    private Score score;
    /**
     * Score à battre
     */
    private int scoreRequis;
    /**
     * Score actuel
     */
    private int scoreTotal;
    /**
     * Nombre de défausses restantes
     */
    private int nbrDefausse;
    /**
     * Nombre de mains à jouer restantes
     */
    private int nbrJeu;
    /**
     * Paquet de défausse utilisé
     */
    private Paquet<CarteJeu> defausse;
    private ArrayList<Integer> couleurs = new ArrayList<Integer>(Arrays.asList(0,0,0,0));
    private ArrayList<Integer> valeurs = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0));

    /**
     * Constructeur de mains, permet de lancer une manche
     *
     * @param j Nombre de mains à jouer
     * @param d Nombre de défausses
     * @param s Score à atteindre
     */
    public Main(int j, int d, int s) {
        super(true);
        this.remplirMain();
        this.score = new Score();
        this.scoreTotal = 0;
        this.defausse = new Deck(true);
        this.scoreRequis = s;
        this.nbrJeu = j;
        this.nbrDefausse = d;

    }

    /**
     * Sélectionne et met à jour les listes d'occurences
     * @param i Position de la carte
     * @param affichage Booleen d'affichage
     */
    public void selectionCarte(int i, boolean affichage) {
        try {
            if (!((this.getNbrSelection() == 5) && (!this.getCarte(i).getEstSelectionnee()))) {
                super.selectionCarte(i, affichage);
                this.updateMains(this.getCarte(i));
            } else {
                System.out.println("Nombre de sélection maximal atteint.");
            }
            if (affichage)
                System.out.println("Nombre de carte sélectionné : " + this.getNbrSelection());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Erreur selectionCarte : Index incorrect");
        }
    }

    /**
     * Methode système pour défausser les cartes sélectionnées
     */
    private void defausser() {
            int i = 0;
            while ((i < this.getSize())) {
                if (this.getCarte(i).getEstSelectionnee()) {
                    this.selectionCarte(i, false);
                    this.defausse.ajoutCarte(this.retirerCarte(i));
                } else
                    i++;
            }
            this.remplirMain();

    }

    /**
     * Méthode utilisateur pour défausser les cartes séléctionnées
     */
    public void utiliserDefausse() throws IllegalArgumentException{
        if (this.getNbrSelection() == 0)
            throw new IllegalArgumentException();
        else if (nbrDefausse > 0) {
            defausser();
            nbrDefausse--;
        } else
            System.out.println("Aucune défausse disponible");
    }

    /**
     * Methode permettant de jouer toutes les cartes de la main
     * @return Booleen si la manche est remportée
     * @throws IllegalArgumentException Aucune carte sélectionnée
     */
    public boolean jouerMain() throws IllegalArgumentException {
        if (this.getNbrSelection() == 0)
            throw new IllegalArgumentException();
        int i = 0;
        int compteur = 0;
        boolean finis = true;
        CarteJeu c;
        while ((i < this.getSize()) && (compteur < this.getNbrSelection())) {
            c = (this.getCarte(i));
            if (c.getEstSelectionnee()) {
                //TODO Verifier si carte participe à la main
                c.jouer(this.score);
                compteur++;
            }
            i++;
        }
        this.scoreTotal += this.score.calculerScoreFinal();
        this.defausser();
        this.score = new Score();
        this.nbrJeu--;
        if ((this.nbrJeu > 0) && (this.scoreTotal < this.scoreRequis)) {
            this.remplirMain();
            finis = false;
        }
        return finis;

    }

    /**
     * Methode remplissant automatiquement le paquet depuis le deck
     */
    public void remplirMain() {
        while (this.getSize() < tailleMain) {
            this.ajoutCarte(Jeu.deck.retirerCarteAlea());
        }
        this.trier(new ValeurComparator());
    }

    public void trier(Comparator<CarteJeu> c){
        this.getCartes().sort(c);
    }

    public Score getScore() {
        return score;
    }

    /**
     * Affichage du score actuel et du score requis, et du nombre d'actions restantes
     */
    public void afficherScore() {
        String str = "\nScore total : " + this.scoreTotal + "\n";
        str += "Score requis : " + this.scoreRequis + "\n";
        str += "Nombre de main restantes : " + this.nbrJeu + "\n";
        str += "Nombre de défausses restantes : " + this.nbrDefausse + "\n";
        str += "Couleurs :" + couleurs.toString() + "\n";
        str += "Valeurs : " + valeurs.toString() + "\n";
        System.out.println(str);
    }

    /**
     * Modifie les valeurs des listes valeurs/couleurs APRES l'avoir sélectionné
     *
     * @param c Carte jouée
     */
    public void updateMains(CarteJeu c) {
        if (c.getEstSelectionnee()){
            couleurs.set(c.getCouleur()-1,couleurs.get(c.getCouleur()-1)+1);
            valeurs.set(c.getValeur()-1,valeurs.get(c.getValeur()-1)+1);
        } else {
            couleurs.set(c.getCouleur()-1,couleurs.get(c.getCouleur()-1)-1);
            valeurs.set(c.getValeur()-1,valeurs.get(c.getValeur()-1)-1);
        }
        this.score = Score.calculerScore(couleurs,valeurs);
    }

    public int getScoreRequis() {
        return scoreRequis;
    }

    public int getScoreTotal() {
        return scoreTotal;
    }

    public String toString(){
        return this.score.toString() + "\n" + super.toString();
    }
}

