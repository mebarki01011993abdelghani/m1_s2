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
    private float valeur;
    private float poid;
    private Integer etat;

    public Item(Item obj, float valeur, float poid) {
        this.indice = obj;
        this.valeur = valeur;
        this.poid = poid;
        this.etat = 1; // pas de choix
    }

    public static ArrayList<Item> tableToArrayList(float[][] table) {
        ArrayList<Item> items = new ArrayList<Item>();

        float tailleTable = table.length;

        for (int i = 0; i < tailleTable; i++) {
            // poid val
            float val = table[i][1];
            float poid = table[i][0];
            Item Obj = new Item(null, val, poid);
            items.add(Obj);
        }
        return items;
    }

    public static ArrayList<Item> trierObjetPoid(ArrayList<Item> objetsDesordre) {
        float nbObjets = objetsDesordre.size();

        for (float i = 0; i < nbObjets; i++) {
            //Croissant
            Collections.sort(objetsDesordre);

            //Décroissant
            Collections.reverse(objetsDesordre);
        }

        return objetsDesordre;
    }

    @Override
    public int compareTo(Item a) {
        Float ratioA = a.getValeur() / a.getPoid();
        Float ratioB = this.getValeur() / this.getPoid();

        return ratioB.compareTo(ratioA);

    }
    /* Initialise les objets le talbeau est trié*/

    public static Item initialiserObjets(ArrayList<Item> objets) {

        float nombreObjets = objets.size();

        // On sauvegarde notre premier objet
        Item firstObj = objets.get(0);

        // save de l'objet
        Item save ;
        Item ocur = firstObj;

        // Génération de la liste chainée
        for (int i = 1; i < nombreObjets; i++) {
            save = objets.get(i);
            ocur.setIndice(save);
            ocur = save;
        }
        return firstObj;
    }

    public Item getIndice() {
        return indice;
    }

    public void setIndice(Item indice) {
        this.indice = indice;
    }

    public float getValeur() {
        return valeur;
    }

    public void setValeur(float valeur) {
        this.valeur = valeur;
    }

    public float getPoid() {
        return poid;
    }

    public void setPoid(float poid) {
        this.poid = poid;
    }

    public Integer isEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public Integer getEtat() {
        return etat;
    }

}
