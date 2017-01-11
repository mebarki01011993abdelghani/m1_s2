package sacados;

import java.util.ArrayList;

public class SacAdos {

    static final int nbObjets = 100;

    static int algorithmGreedy() {
        int valeurTotal = 0;
        
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
     
    }

}
