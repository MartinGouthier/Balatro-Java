import java.util.Comparator;

public class ValeurComparator implements Comparator<CarteJeu> {

    @Override
    public int compare(CarteJeu o1, CarteJeu o2) {
        return Integer.compare(o1.getValeur(), o2.getValeur());
    }
}
