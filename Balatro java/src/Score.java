import java.util.ArrayList;

public class Score {
    private int chips;
    private double mult;


    public Score(int c, double m){
        this.chips = c;
        this.mult = m;

    }

    public Score(){
        this.chips = 0;
        this.mult = 0;
    }

    public int calculerScoreFinal(){
        System.out.println((int)(chips * mult));
        return (int)(chips*mult);
    }

    public void setMult(double m){
        this.mult = m;
    }

    public void ajouterMult(double m){
        this.mult += m;
    }

    public void multiplierMult(double m){
        this.mult *= m;
    }

    public void ajouterChips(int c){
        this.chips += c;
    }

    @Override
    public String toString() {
        return "(" +
                chips +
                "*" + mult +
                ')';
    }

    public static Score calculerScore(ArrayList<Integer> couleurs, ArrayList<Integer> valeurs){
        boolean suite = false;

        for (int i = 0;i<10;i++) {
            int compteur = 0;
            for (int j = i; j < (i + 5); j++) {
                if (valeurs.get(j%13) == 1) {
                    compteur++;
                } else {
                    break;
                }
            }
            if (compteur == 5){
                suite = true;
                break;
            }
        }

        boolean doublePair = false;
        int index = valeurs.indexOf(2);
        if (index != -1){
            for (int i = index+1;i<valeurs.size();i++){
                if (valeurs.get(i) == 2){
                    doublePair = true;
                    break;
                }
            }
        }
        int chips;
        double mult;
        if (suite&&couleurs.contains(5)){ // Quinte flush
            chips = 100;
            mult = 8;
        } else if (valeurs.contains(4)) { // Carré
            chips = 60;
            mult= 7;
        } else if (valeurs.contains(3)&&valeurs.contains(2)){ // Main pleine
            chips = 40;
            mult = 4;
        } else if (couleurs.contains(5)){ // Couleur
            chips = 35;
            mult = 4;
        } else if (suite){ // Suite
            chips = 30;
            mult = 4;
        } else if (valeurs.contains(3)){ // Brelan
            chips = 30;
            mult = 3;
        } else if (doublePair){
            chips = 20;
            mult = 2;
        } else if (valeurs.contains(2)){
            chips = 10;
            mult = 2;
        } else if (valeurs.contains(1)){
            chips = 5;
            mult = 1;
        } else {
            chips = 0;
            mult = 0;
        }
        return new Score(chips,mult);
    }


    @Override
    public boolean equals(Object o){
        Score s = (Score) o;
        return (s.mult==this.mult&&s.chips==this.chips);
    }
}
