import java.util.ArrayList;
import java.util.Random;

public class Paquet {
    /*
    private Carte[] cartes;
    private int nbrCarte;
    */

    private ArrayList<Carte> cartes;


    public Paquet(){
        this.cartes = new ArrayList<>();

    }

    public void ajoutCarte(Carte c){
        cartes.add(c);
    }

    public ArrayList<Carte> getCartes(){
        return cartes;
    }

    public Carte retirerCarte(int p){
        if (p>=cartes.size())
            throw new IndexOutOfBoundsException();
        Carte c = cartes.get(p);
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
            this.cartes.get(i).setEstSelectionnee();
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



}
