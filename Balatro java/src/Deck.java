import java.util.ArrayList;
import java.util.Random;

public class Deck {

    /**Nombre de carte sélectionné*/
    protected int nbrSelection;

    /**Paquet de cartes*/
    protected ArrayList<CarteJeu> cartes;

    public Deck(boolean estVide){
        this.cartes = new ArrayList<>();
        this.nbrSelection = 0;
        if (!estVide) {
            for (int i = 1; i < 5; i++) {
                for (int j = 1; j < 14; j++) {
                    this.ajoutCarte(new CarteJeu(j, i));
                }
            }
        }
    }

    public void ajoutCarte(CarteJeu c){
        cartes.add(c);
    }

    public ArrayList<CarteJeu> getCartes(){
        return cartes;
    }

    public Carte retirerCarte(int p){
        if (p>=cartes.size())
            throw new IndexOutOfBoundsException();
        CarteJeu c = cartes.get(p);
        cartes.remove(c);
        return c;
    }

    public Carte retirerCarteAlea(){
        Random rand = new Random();
        int p = rand.nextInt(cartes.size());
        return this.retirerCarte(p);
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        for (int i = 0;i<this.cartes.size();i++) {
            str.append(i+1).append(". ").append(this.cartes.get(i).toString(false)).append("\n");
        }
        return str.toString();
    }

    /**
     * Lève ou baisse une carte
     * @param i Position de la carte
     * @exception IndexOutOfBoundsException Valeur i négatif ou supérieur au nombre de carte
     */
    public void selectionCarte(int i, boolean affichage) throws IndexOutOfBoundsException{
        if (i>=cartes.size())
            throw new IndexOutOfBoundsException();
        else {
            this.cartes.get(i).prendreCarte();
            if (this.cartes.get(i).getEstSelectionnee()){
                nbrSelection++;
            } else
                nbrSelection--;
            if (affichage) {
                System.out.print("La carte " + this.cartes.get(i).toString(true) + " a été ");
                if (this.cartes.get(i).getEstSelectionnee()) {
                    System.out.println("sélectionné.");
                } else
                    System.out.println("désélectionné.");
            }
        }

    }

    public int getSize(){
        return this.cartes.size();
    }

    public void echangerPlaces() {
        if (nbrSelection==2){
            CarteJeu c1 = null;
            CarteJeu c2 = null;
            for (CarteJeu c : cartes){
                if (c1 == null&&c.getEstSelectionnee()){
                    c1 = c;
                } else if (c.getEstSelectionnee()){
                    c2 = c;
                }
            }
            int i = cartes.indexOf(c2);
            cartes.set(cartes.indexOf(c1),c2);
            cartes.set(i,c1);
            this.nbrSelection = 0;
            cartes.get(cartes.indexOf(c1)).prendreCarte();
            cartes.get(cartes.indexOf(c2)).prendreCarte();

        } else
            throw new IllegalArgumentException("2 cartes nécessaires pour cette action");
    }


}
