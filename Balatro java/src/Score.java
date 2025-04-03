
public class Score {
    private int chips;
    private double mult;


    public Score(int c, double m){
        this.chips = c;
        this.mult = m;

    }

    public Score(){
        this.chips = 0;
        this.mult = 10;
    }

    public double calculerScoreFinal(){
        return chips*mult;
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
}
