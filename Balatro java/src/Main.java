import java.util.ArrayList;

public class Main extends Deck{

    /**Taille maximale de la main*/
    public static int tailleMain = 8;
    /**Jetons et Mult actuels*/
    private Score score;
    /**Score à battre*/
    public int scoreRequis;
    /**Score actuel*/
    public int scoreTotal;
    /**Nombre de défausse restantes*/
    public int nbrDefausse;
    /**Nombre de main à jouer restantes*/
    public int nbrJeu;
    /**Deck de cartes utilisé*/
    private Deck deck;
    /**Paquet de défausse utilisé*/
    private Paquet defausse;
    public static ArrayList<Integer> couleurs = new ArrayList<Integer>(4);
    public static ArrayList<Integer> valeurs = new ArrayList<Integer>(13);

    /**
     * Constructeur de mains, permet de lancer une manche
     * @param j Nombre de mains à jouer
     * @param d Nombre de défausses
     * @param s Score à atteindre
     * @param de Deck utilisé
     */
    public Main(int j, int d, int s, Deck de){
        super(true);
        this.cartes = new ArrayList<>();
        this.deck = de;
        this.remplirMain();
        this.score = new Score();
        this.scoreTotal = 0;
        this.defausse = new Paquet();
        this.scoreRequis = s;
        this.nbrJeu = j;
        this.nbrDefausse = d;

    }

    public void selectionCarte(int i){
        try {
            if (!((nbrSelection == 5) && (!this.getCartes().get(i).getEstSelectionnee()))) {
                super.selectionCarte(i, true);
            } else {
                System.out.println("Nombre de sélection maximal atteint.");
            }
            System.out.println("Nombre de carte sélectionné : " + nbrSelection);
        } catch (IndexOutOfBoundsException e ){
            System.out.println("Erreur selectionCarte : Index incorrect");
        }
    }

    public void defausser(){
        if (nbrSelection == 0)
            System.out.println("Erreur : Aucune carte a défausser");
        else {
            int i = 0;
            while ((i < this.getSize())){
                if (this.getCartes().get(i).getEstSelectionnee()) {
                    super.selectionCarte(i, false);
                    this.defausse.ajoutCarte(this.retirerCarte(i));
                }
                else
                    i++;
            }
            this.remplirMain();
        }
    }

    public void utiliserDefausse(){
        if (nbrDefausse > 0){
            defausser();
            nbrDefausse--;
            nbrSelection = 0;
            System.out.println(this.afficherScore());
        }
        else
            System.out.println("Aucune défausse disponible");
    }

    /**
     * @return
     * @throws IllegalArgumentException
     */
    public boolean jouerMain() throws IllegalArgumentException {
        if (nbrSelection == 0)
            throw new IllegalArgumentException();
        int i = 0;
        int compteur = 0;
        boolean finis = true;
        CarteJeu c;
        while ((i < this.getSize()) && (compteur < nbrSelection)) {
            c = (this.getCartes().get(i));
            if (c.getEstSelectionnee()) {
                this.score = c.jouer(this.score);
                compteur++;
            }
            i++;
        }
        this.defausser();
        this.scoreTotal += (int) this.score.calculerScoreFinal();
        this.score = new Score();
        this.nbrJeu--;
        this.nbrSelection = 0;
        if ((this.nbrJeu>0)&&(this.scoreTotal<this.scoreRequis)) {
            this.remplirMain();
            finis = false;
        }
        System.out.println(this.afficherScore());
        return finis;

    }

    public void remplirMain(){
        while (this.getSize()<tailleMain){
            this.ajoutCarte((CarteJeu) this.deck.retirerCarteAlea());
        }
    }

    public Score getScore() {
        return score;
    }

    public String afficherScore(){
        String str = "\nScore total : " + this.scoreTotal+"\n";
        str += "Score requis : " + this.scoreRequis+"\n";
        str += "Nombre de main restantes : " + this.nbrJeu+"\n";
        str += "Nombre de défausses restantes : " + this.nbrDefausse;
        return str;
    }

    /**
     * Modifie les valeurs des listes valeurs/couleurs
     * @param c Carte
     */
    public void updateMains(CarteJeu c){
        //TODO Mains jouables
    }
}
