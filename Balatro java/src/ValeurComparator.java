import java.util.Comparator;

public class ValeurComparator implements Comparator<CarteJeu> {

    @Override
    public int compare(CarteJeu o1, CarteJeu o2) {
        int i = Integer.compare(o1.getValeur(), o2.getValeur());
        if (i == 0){
            return Integer.compare(o1.getCouleur(),o2.getCouleur());
        } else
            return i;
    }
}
