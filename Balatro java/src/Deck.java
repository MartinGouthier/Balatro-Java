import java.util.ArrayList;
import java.util.Random;

public class Deck extends Paquet<CarteJeu> {


    public Deck(boolean estVide){
        super();
        if (!estVide) {
            for (int i = 1; i < 5; i++) {
                for (int j = 1; j < 14; j++) {
                    this.ajoutCarte(new CarteJeu(j, i));
                }
            }
        }
    }


}
