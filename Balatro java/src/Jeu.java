import java.util.Random;
import java.util.Scanner;

public class Jeu {

    public static int nbrDefausseTotal = 3;
    public static int nbrJeuTotal = 3;
    public static int argent = 4;
    public static Deck deck;

    public static void main(String[] args) throws Exception {
        deck = new Deck();
        int[] antes = {150, 300, 800, 1500};
        boolean gagne = lancerManche(300);
        int ante = 1;
        double blind = 1;
        while (gagne && (ante < antes.length)) {
            while (gagne && (blind <= 2)) {
                gagne = lancerManche((int) (antes[ante] * blind));
                blind = blind + 0.5;
            }
            blind = 1;
            ante++;
        }

    }

    /**
     * Lance une manche de jeu
     *
     * @param score Score à battre
     * @return Reussite de la manche
     */
    public static boolean lancerManche(int score) throws Exception {
        Main m = new Main(nbrJeuTotal, nbrDefausseTotal, score, deck);
        System.out.println("Score actuel: " + m.getScore().toString());
        System.out.println(m);
        boolean finis = false;
        Scanner sc = new Scanner(System.in);
        String action = sc.nextLine();
        boolean formatActionOK;
        int n;
        while (!finis) {

            formatActionOK = false;
            n = -1;
            while (!formatActionOK) {
                try {
                    n = Integer.parseInt(action);
                    formatActionOK = true;
                } catch (NumberFormatException e) {
                    if (action.equals("j") || (action.equals("d")) || (action.equals("l"))) {
                        formatActionOK = true;
                    } else {
                        System.out.println("Saisie Incorrecte, réessayez:");
                        action = sc.nextLine();
                    }
                }
            }
            if (n > 0) {
                m.selectionCarte(n - 1);
            } else if (action.equals("l"))
                throw new Exception("Arret de la partie");
            else if (action.equals("j")) {
                try {
                    finis = m.jouerMain();
                } catch (IllegalArgumentException e) {
                    System.out.println("Aucune carte sélectionnée");
                }
            } else if (action.equals("d"))
                m.utiliserDefausse();
            if (!finis) {
                System.out.println("Score actuel: " + m.getScore().toString());
                System.out.println(m);
                action = sc.nextLine();
            }

        }
        return (m.scoreTotal >= m.scoreRequis);

    }

    public static boolean calculerProba(int num, int den) {
        Random rand = new Random();
        int n = rand.nextInt(den) + 1;
        return n <= num;
    }

}
/*
A faire:
Classe différentes mains
Methode jouer cartes
 */