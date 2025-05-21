import java.util.ArrayList;
import java.util.Random;

public abstract class Paquet<T extends Carte> {

    /**Nombre de carte sélectionné*/
    private int nbrSelection;

    /**
     * Liste de cartes du paquet
     */
    private ArrayList<T> cartes;

    /**
     * Constructeur vide de paquet
     */
    public Paquet(){
        this.cartes = new ArrayList<>();
        this.nbrSelection = 0;

    }

    /**
     * Ajout d'une carte dans un paquet
     * @param c Carte ajoutée
     */
    public void ajoutCarte(T c){
        cartes.add(c);
    }

    /**
     * Getter de cartes
     * @return
     */
    public ArrayList<T> getCartes(){
        return cartes;
    }

    public T getCarte(int i){
        return cartes.get(i);
    }

    public void enleverCarte(Carte c){
        this.cartes.remove(c);
    }

    public void enleverCarte(int i){
        this.cartes.remove(i);
    }

    /**
     * Retire une carte précise du paquet
     * @param p Indice de la carte
     * @return Carte retirée
     */
    public T retirerCarte(int p){
        if (p>=this.getSize())
            throw new IndexOutOfBoundsException("Indice incorrect");
        T c = cartes.get(p);
        cartes.remove(c);
        return c;
    }

    /**
     * Retire une carte aléatoire du paquet
     * @return Carte retirée
     */
    public T retirerCarteAlea(){
        Random rand = new Random();
        int p = rand.nextInt(this.getSize());
        return this.retirerCarte(p);
    }

    /**
     * Affichage du paquet
     * @return Affichage
     */
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

    /**
     * Getter taille du paquet
     * @return taille du paquet
     */
    public int getSize(){
        return this.cartes.size();
    }

    /**
     * Getter nombre de sélecton
     * @return Nombre de cartes séléctionnées
     */

    public int getNbrSelection() {
        return nbrSelection;
    }

    /**
     * Methode pour échanger de place 2 cartes sélectionnées
     */
    public void echangerPlaces() {
        if (nbrSelection==2){
            T c1 = null;
            T c2 = null;
            for (T c : cartes){
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
