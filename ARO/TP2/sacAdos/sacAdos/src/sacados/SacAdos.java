package sacados;

import java.io.IOException;
import java.util.ArrayList;

public class SacAdos {

    static int poidSac;
    static final String chemin = "src/sacados/sac1.txt";
    static float minorant;
    static float majorant;

    static float algorithemGloutonMajorant(Item firstItem) {
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

            } else {
                valeurTotal = valeurTotal + obj.getValeur();
                poidTotal = verifPoid;
            }

            // on récupere l'objet suivant
            obj = obj.getIndice();
        }

        return valeurTotal;
    }

    static float algorithemGloutonMinorant(Item firstItem) {
        int poidTotal = 0, verifPoid = 0, i = 0;
        float valeurTotal = 0;

        Item obj = firstItem;

        while (poidTotal < poidSac && obj != null) {
            verifPoid = poidTotal + (int) obj.getPoid();
            if (verifPoid < poidSac) {

                valeurTotal = valeurTotal + obj.getValeur();
                poidTotal = verifPoid;
            } else {

            }

            // on récupere l'objet suivant
            obj = obj.getIndice();

        }
        return valeurTotal;
    }

    public static float algorithmeBranchAndBound(Item firstItem) {
        float valeurTotal = 0;
        float valCourante = 0;
        Item obj = firstItem;
        Item lastObj = firstItem;
        /*Algorithme glouton*/
        valCourante = (int) algorithemGloutonMajorant(obj);

        while (valeurTotal < majorant || obj != null) {
            if (valCourante > minorant) {
                lastObj = obj;
                obj = obj.getIndice();
                obj.setEtat(1);
                valCourante = algorithemGloutonMajorant(obj);
            } else {
                obj = lastObj.getIndice().getIndice();
                obj.setEtat(0);
                valCourante = algorithemGloutonMajorant(obj);

            }
        }
        return valeurTotal;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Item> objets = new ArrayList<Item>();
        ArrayList<Item> objetsNonTrie = new ArrayList<Item>();

        float[][] objs;

        /* Initialisation */
        objs = ReadFile.getTable(chemin);
        /*Créer objets*/
        objetsNonTrie = Item.tableToArrayList(objs);
        objets = Item.trierObjetPoid(objetsNonTrie);

        Item firstObj = Item.initialiserObjets(objets);

        majorant = algorithemGloutonMajorant(firstObj);
        minorant = algorithemGloutonMinorant(firstObj);

        System.out.println("Version majorante " + majorant);
        System.out.println("Version minorant " + minorant);
        System.out.println("Soit la solution x est borné par : (" + minorant + "<=x<=" + majorant + ")");

        algorithmeBranchAndBound(firstObj);
    }

}
