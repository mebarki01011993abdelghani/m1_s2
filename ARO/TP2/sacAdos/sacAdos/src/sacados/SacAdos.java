package sacados;

import java.io.IOException;
import java.util.ArrayList;

public class SacAdos {

    static int poidSac;
    static final String chemin = "src/sacados/sac1.txt";
    static float minorant;
    static float majorant;
    static int cmpt = 0;

    static float algorithmeGloutonMajorant(Item firstItem, float valCourante, float poidCourant) {
        int poidTotal = (int) poidCourant, verifPoid = 0;
        float val, depassement, valeurTotal = valCourante;

        Item obj = firstItem.getIndice();// etat suivant de l'état courant

        // On regarde si l'objet courant est ajouté dans le sac 
        if (firstItem.getEtat() == 1) {
            poidTotal = poidTotal + (int) firstItem.getPoid();
            valeurTotal = valeurTotal + firstItem.getValeur();
        }

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

    static float algorithmeGloutonMinorant(Item firstItem, float valCourante, float poidCourant) {
        int poidTotal = (int) poidCourant, verifPoid = 0;
        float valeurTotal = valCourante;
        Item obj = firstItem.getIndice(); // etat suivant de l'état courant

        // On regarde si l'objet courant est ajouté dans le sac 
        if (firstItem.getEtat() == 1) {
            poidTotal = poidTotal + (int) firstItem.getPoid();
            valeurTotal = valeurTotal + firstItem.getValeur();
        }

        while (poidTotal < poidSac && obj != null) {
            verifPoid = poidTotal + (int) obj.getPoid();
            if (verifPoid < poidSac) {
                valeurTotal = valeurTotal + obj.getValeur();
                poidTotal = verifPoid;
            }

            // on récupere l'objet suivant
            obj = obj.getIndice();
        }
        return valeurTotal;
    }

    public static void algorithmeBranchAndBound(Item itemCourant, float valCourante, float poidCourant) {
        float valMin = 0;
        float valMax = 0;

        /*Algorithme glouton*/
        valMax = algorithmeGloutonMajorant(itemCourant, valCourante, poidCourant);
        if (valMax > minorant) {
                    cmpt++;

            valMin = algorithmeGloutonMinorant(itemCourant, valCourante, poidCourant);

            if (valMin > minorant) {
                minorant = valMin;
            }

            /*On avance dans l'arbre*/
            Item next = itemCourant.getIndice();

            // On sauvegarde nos valeurs
            if (itemCourant.getEtat() == 1) {
                valCourante = valCourante + itemCourant.getValeur();
                poidCourant = poidCourant + itemCourant.getPoid();
            }

            if (next != null) {

                // On ne doit pas dépasser le poid du sac
                if (next.getPoid() + poidCourant <= poidSac) {
                    next.setEtat(0);
                    algorithmeBranchAndBound(next, valCourante, poidCourant);
                    next.setEtat(1);
                    algorithmeBranchAndBound(next, valCourante, poidCourant);
                } else {
                    next.setEtat(0);
                    algorithmeBranchAndBound(next, valCourante, poidCourant);
                }
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

        majorant = algorithmeGloutonMajorant(firstObj, 0, 0);
        minorant = algorithmeGloutonMinorant(firstObj, 0, 0);

        System.out.println("Version majorante " + majorant);
        System.out.println("Version minorant " + minorant);
        System.out.println("Soit la solution x est borné par : (" + minorant + "<=x<=" + majorant + ")");

        // On déroule l'arbre
        firstObj.setEtat(1);
        algorithmeBranchAndBound(firstObj, 0, 0);
        firstObj.setEtat(0);
        algorithmeBranchAndBound(firstObj, 0, 0);

        System.out.println("RESULTAT : " + minorant);
        System.out.println("noeud  " + cmpt);

    }

}
