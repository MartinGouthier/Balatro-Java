/**
 * Classe Joker
 * Activations des jokers:
 * 1 : Debut jouer
 * 2 : Par carte jouée
 * 3 : Par carte jouée au premier tour
 * 4 : Par carte non jouée
 * 5 : Jokers indépendants
 * 6 : Par Défausse
 * 7 : Passif
 */

public class Joker extends AutreCarte  {

    private final int idJoker;
    private String nom;
    private boolean retourne;
    private int edition;

    public Joker(int idJoker, int prix, int neg) {
        super(prix, neg);
        this.idJoker = idJoker;
        this.retourne = false;

    }

    public Joker(Joker joker){
        super(joker.getPrixVente(),0);
        this.idJoker = joker.getIdJoker();
        this.retourne = false;
        this.edition = joker.getEdition();
    }

    public void jouer(Score s) {
        //TODO Effet joker
        marquerPoints(s);
    }

    public void marquerPoints(Score s) {

    }

    @Override
    public String toString(boolean b) {
        return "";
    }

    public int getIdJoker() {
        return idJoker;
    }


    public void retourner() {
        this.retourne = !this.retourne;
    }

    public boolean estRetourne() {
        return retourne;
    }

    public int getEdition() {
        return edition;
    }
}
