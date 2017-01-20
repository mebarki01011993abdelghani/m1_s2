package sacados;

import java.io.IOException;
import java.util.ArrayList;

public class SacAdos {

    static int poidSac;
    static final String chemin = "src/sacados/sac1.txt";
    static float minorant;
    static float majorant;

    static float algorithmeGloutonMajorant(Item firstItem) {
        int poidTotal = 0, verifPoid = 0, i = 0;
        float val, depassement, valeurTotal = 0;

        Item obj = firstItem;

        while (poidTotal < poidSac && obj != null) {
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

    static float algorithmeGloutonMinorant(Item firstItem) {
        int poidTotal = 0, verifPoid = 0, i = 0;
        float valeurTotal = 0;
        Item obj = firstItem;

        while (poidTotal < poidSac && obj != null) {
            if (obj.getEtat() == 1) {
                verifPoid = poidTotal + (int) obj.getPoid();
                if (verifPoid < poidSac) {
                    valeurTotal = valeurTotal + obj.getValeur();
                    poidTotal = verifPoid;
                }
            }
            // on récupere l'objet suivant
            obj = obj.getIndice();
        }
        return valeurTotal;
    }

    public static void algorithmeBranchAndBound(Item itemCourant) {
        float valMin = 0;
        float valMax = 0;

        /*Algorithme glouton*/
        valMax = algorithmeGloutonMajorant(itemCourant);
        if (valMax > minorant) {
            valMin = algorithmeGloutonMinorant(itemCourant);
            System.out.println("val " + valMin);
            if (valMin > minorant) {
                minorant = valMin;
            }

            Item next = itemCourant.getIndice();
            if (next != null) {
                next.setEtat(0);
                algorithmeBranchAndBound(next);
                next.setEtat(1);
                algorithmeBranchAndBound(next);
            }
        }
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

        while (firstObj != null) {
            System.out.println(" valeur " + firstObj.getValeur());
            System.out.println(" poid " + firstObj.getPoid());

            firstObj = firstObj.getIndice();
        }
        majorant = algorithmeGloutonMajorant(firstObj);
        minorant = algorithmeGloutonMinorant(firstObj);

        System.out.println("Version majorante " + majorant);
        System.out.println("Version minorant " + minorant);
        System.out.println("Soit la solution x est borné par : (" + minorant + "<=x<=" + majorant + ")");

        firstObj.setEtat(1);
        algorithmeBranchAndBound(firstObj);
        firstObj.setEtat(0);
        algorithmeBranchAndBound(firstObj);

        System.out.println("Minorant : " + minorant);

    }

}
