public class Deck extends Paquet{

    public Deck(){
        super();
        for (int i = 1;i<5;i++){
            for (int j = 1;j<14;j++){
                this.ajoutCarte(new CarteJeu(j,i));
            }
        }
    }

    public void ajouterNouvCarte(Carte c){
        this.ajoutCarte(c);
    }


}
