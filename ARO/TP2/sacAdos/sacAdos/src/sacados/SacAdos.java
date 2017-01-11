package sacados;

import java.util.ArrayList;

public class SacAdos {

    static final int poidSac = 1000;

    static double algorithmGreedy(Item firstItem) {
        double valeurTotal = 0;
        double poidTotal = 0;


        int i = 0;
        Item obj = firstItem;
        double val ;
        double verif;
        
        while (poidTotal < poidSac) {
            verif = poidTotal + obj.getPoid();
            if (verif > valeurTotal) {
                poidTotal = poidTotal + (poidSac - poidTotal);
                val = (poidSac - poidTotal)/obj.getPoid();
                val = obj.getValeur()*val;
                valeurTotal = valeurTotal + val;
            } else {
                valeurTotal = verif;
                poidTotal = poidTotal + obj.getPoid();
            }
            // on récupere l'objet suivant
            obj = obj.getIndice();
        }

        return valeurTotal;
    }

    public static void main(String[] args) {
        ArrayList<Item> objets = new ArrayList<Item>();
        int[][] objs = new int[5][2];
        objs[0][1] = 3;
        objs[1][0] = 2;
        objs[1][1] = 5;
        objs[2][0] = 9;
        objs[2][1] = 11;
        objs[3][0] = 9;
        objs[3][1] = 554;
        objs[4][0] = 8;
        objs[4][1] = 5;

        /*Créer objet*/
        objets = Item.tableToArrayList(objs);
        objets = Item.trierObjetPoid(objets);
        Item firstObj = Item.initialiserObjets(objets);
        algorithmGreedy(firstObj);

    }

}
