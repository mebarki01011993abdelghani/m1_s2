package sacados;

import java.io.IOException;
import java.util.ArrayList;

public class SacAdos {

    static int poidSac;
    static final String chemin = "src/sacados/sac1.txt";

    static double algorithmGreedy(Item firstItem) {
        int poidTotal = 0, verifPoid = 0, i = 0;
        float val, depassement, valeurTotal = 0;

        Item obj = firstItem;

        while (poidTotal < poidSac) {
            verifPoid = poidTotal + (int) obj.getPoid();
            if (verifPoid > poidSac) {
                //On calcul le dépassement
                depassement = verifPoid - poidSac;
                //On calcul le "découpement" de l'objet
                val = (obj.getPoid() - depassement) / obj.getPoid();
                //On calcul sa valeur fractionnaire
                val = obj.getValeur() * val;
                // On l'ajouter à la valeur total
                valeurTotal = valeurTotal + val;
                poidTotal = poidSac;
                obj.setEtat(true);

            } else {
                valeurTotal = valeurTotal + obj.getValeur();
                poidTotal = verifPoid;
                obj.setEtat(true);
            }

            // on récupere l'objet suivant
            obj = obj.getIndice();
        }

        return valeurTotal;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Item> objets = new ArrayList<Item>();
        float[][] objs;

        /* Initialisation */
        objs = ReadFile.getTable(chemin);
        /*Créer objets*/
        objets = Item.tableToArrayList(objs);
        objets = Item.trierObjetPoid(objets);

        Item firstObj = Item.initialiserObjets(objets);
        System.out.println(algorithmGreedy(firstObj));

    }

}
