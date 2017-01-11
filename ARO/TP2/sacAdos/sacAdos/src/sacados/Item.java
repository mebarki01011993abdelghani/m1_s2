/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sacados;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author b16007026
 */
public class Item implements Comparable<Item> {

    private Item indice;
    private int valeur;
    private int poid;
    private boolean etat;

    public Item(boolean etat, Item obj, int valeur, int poid) {
        this.indice = obj;
        this.valeur = valeur;
        this.poid = poid;
        this.etat = etat;
    }

    public static ArrayList<Item> tableToArrayList(int[][] table) {
        ArrayList<Item> items = new ArrayList<Item>();

        int tailleTable = table.length;

        for (int i = 0; i < tailleTable; i++) {
            // val poid
            int val = table[i][0];
            int poid = table[i][1];
            Item Obj = new Item(false, null, val, poid);
            items.add(Obj);
        }
        return items;
    }

    public static ArrayList<Item> trierObjetPoid(ArrayList<Item> objetsDesordre) {
        int nbObjets = objetsDesordre.size();

        for (int i = 0; i < nbObjets; i++) {
            Collections.sort(objetsDesordre);
            Collections.reverse(objetsDesordre);
        }
        return objetsDesordre;
    }

    @Override
    public int compareTo(Item a) {
        Double ratioA = (double) a.getValeur() / (double) a.getPoid();
        Double ratioB = (double) this.getValeur() / (double) this.getPoid();

        return ratioB.compareTo(ratioA);

    }
    /* Initialise les objets le talbeau est trié*/

    public static Item initialiserObjets(ArrayList<Item> objets) {

        int nombreObjets = objets.size();

        // On sauvegarde notre premier objet
        Item firstObj = objets.get(0);

        // save de l'objet
        Item save = firstObj;

        // Génération de la liste chainée
        for (int i = 1; i < nombreObjets; i++) {
            save.setIndice(save);
            save = objets.get(i);
        }

        return firstObj;
    }

    public Item getIndice() {
        return indice;
    }

    public void setIndice(Item indice) {
        this.indice = indice;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public int getPoid() {
        return poid;
    }

    public void setPoid(int poid) {
        this.poid = poid;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

}
