/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author scra
 */
public class Main {

    public static TreeMap<String, Integer> treilli = new TreeMap<String, Integer>();
    public final static int min = 2; // 2 occurences est le min
    /*METHODES*/
    /*Convertit un treilli au format string en un arrayList*/

    public void afficherTreeMap(TreeMap<String, Integer> hash) {
        for (String mapKey : hash.keySet()) {
            System.out.println("ITEM : " + mapKey + " -- OCCURENRENCES " + hash.get(mapKey));
        }
    }

    public ArrayList<ArrayList<Apriori>> stringToCorrectFormat(String modele) {
        ArrayList<ArrayList<Apriori>> liste = new ArrayList<ArrayList<Apriori>>();
        /*Décomposition*/
        /*Ligne par ligne*/
        String[] lignes = modele.split("\n");
        /*On supprime les deux premieres lignes en commencant à i = 2*/
        int nbrsLigne = lignes.length;

        for (int i = 2; i < nbrsLigne; i++) {
            String[] vals = lignes[i].split(" ");
            // on supprime les TIDS : i commence à 1
            int taille = vals.length;
            ArrayList<Apriori> collections = new ArrayList<Apriori>();
            liste.add(collections);
            for (int j = 1; j < taille; j++) {
                liste.get(i - 2).add(new Apriori(vals[j])); // chaque élément est un item, un item est un string
                /*Affichage check*/
                //System.out.println(liste.get(i-2).get(j-1).getItem());
            }
        }

        return liste;
    }

    /*Compte le nombre d'item dans un treilli*/
    public void compterItems(ArrayList<ArrayList<Apriori>> liste) {
        int taille = liste.size();
        int tailleCollection = 0;
        int count = 0;
        for (int i = 0; i < taille; i++) {
            tailleCollection = liste.get(i).size();
            for (int j = 0; j < tailleCollection; j++) {
                String ite = liste.get(i).get(j).getItem();
                if (treilli.containsKey(ite)) {
                    count = treilli.get(ite) + 1;
                    treilli.put(ite, count);
                } else {
                    treilli.put(ite, 1);
                }
            }
        }
    }

    /*afficher arrayList*/
    public void afficherListe(ArrayList<ArrayList<Apriori>> liste) {
        int tailleListe = liste.size();
        int tailleCollection = 0;
        for (int i = 0; i < tailleListe; i++) {
            tailleCollection = liste.get(i).size();
            for (int j = 0; j < tailleCollection; j++) {
                System.out.print("ITEM : " + liste.get(i).get(j).getItem() + " -- Occurence " + liste.get(i).get(j).getOccurence() + "\n");
            }
        }
    }


    /*Générer combinaison*/
    public ArrayList<ArrayList<Apriori>> combinaison() {
        /*On check si la taille des itemspour les combinaisons*/
        ArrayList<ArrayList<Apriori>> donneesLn_1 = new ArrayList<ArrayList<Apriori>>();
        Map.Entry<String, Integer> entry = treilli.entrySet().iterator().next();
        String key = entry.getKey();

        if (key.length() == 1) {
            donneesLn_1 = combinaisonTailleUne(treilli);
        } else {
            donneesLn_1 = combinaisonTailleN(treilli);
        }
        return donneesLn_1;

    }

    /*Combinaison de taille une*/
    private static ArrayList<ArrayList<Apriori>> combinaisonTailleUne(TreeMap<String, Integer> donnees) {

        ArrayList<ArrayList<Apriori>> donneesLn = new ArrayList<ArrayList<Apriori>>();

        /*On change le format*/
        ArrayList<Apriori> newDonnees = new ArrayList<Apriori>();

        for (String mapKey : donnees.keySet()) {
            newDonnees.add(new Apriori(mapKey));
        }
        /**
         * ****************************************************
         */
        for (int i = 0; i < newDonnees.size() - 1; i++) {
            String firstItem = newDonnees.get(i).getItem();
            ArrayList<Apriori> ar = new ArrayList<Apriori>();
            donneesLn.add(ar);
            for (int j = i + 1; j < donnees.size(); j++) {
                String nextItem = newDonnees.get(j).getItem();
                /*Génération de la nouvelle données*/
                String result = firstItem.concat(nextItem);
                Apriori obj = new Apriori(result);
                //add
                ar.add(obj);
            }

        }

        return donneesLn;
    }

    /*Combinaison de taille N différent de 1*/
    private static ArrayList<ArrayList<Apriori>> combinaisonTailleN(TreeMap<String, Integer> donnees) {
        ArrayList<ArrayList<Apriori>> donneesLn = new ArrayList<ArrayList<Apriori>>();

        /*On change le format*/
        ArrayList<Apriori> newDonnees = new ArrayList<Apriori>();

        for (String mapKey : donnees.keySet()) {
            newDonnees.add(new Apriori(mapKey));
        }
        boolean find = false;
        // Si les données sont de taille "1"
        for (int i = 0; i < newDonnees.size() - 1; i++) {
            String firstItem = newDonnees.get(i).getItem();
            /*Récupérer la deniere lettre*/
            char lastLetter = firstItem.toCharArray()[firstItem.length() - 1];
            ArrayList<Apriori> ar = new ArrayList<Apriori>();
            donneesLn.add(ar);
            for (int j = i + 1; j < newDonnees.size(); j++) {
                String nextItem = newDonnees.get(j).getItem();
                /*On prend la premier lettre de la chaine*/
                char compare = nextItem.toCharArray()[0];
                if (compare == lastLetter) {
                    find = true;
                    /*On récupére les lettres suivantes*/
                    String lastLetters = nextItem.substring(1, nextItem.length());
                    /*Génération de la nouvelle données*/
                    String result = firstItem.concat(lastLetters);
                    Apriori obj = new Apriori(result);
                    //add
                    ar.add(obj);
                } //Si on ne trouve pas de composition apres en avoir trouvé au moins une on s'arrete
                else if (find == true) {
                    find = false;
                    break;
                }
            }
        }
        return donneesLn;
    }

    /*On vérifie si il y a des infréquents, retourne seulement les fréquents*/
    public static ArrayList<ArrayList<Apriori>> checkMins(ArrayList<ArrayList<Apriori>> ensemble) {
        int tailleEnsemble = ensemble.size();
        int tailleCollections = 0;
        for (int i = 0; i < tailleEnsemble; i++) {
            tailleCollections = ensemble.get(i).size();
            for (int j = 0; j < tailleCollections; j++) {
                Apriori it = ensemble.get(i).get(j);
                int nbs = treilli.get(it.getItem()); //on get le nombre d'occurences
                if (nbs < min) {
                    //ArrayList<Apriori> ite = ensemble.get(i);
                    // ite.remove(j); // on supprime l'item
                    treilli.remove(it.getItem());
                }
            }
        }
        return ensemble;
    }

    /*public void aprioriAlgo(ArrayList<Apriori> ensemble) {
     ensemble = checkMins(ensemble);
     afficherListe(ensemble);

     while (ensemble.size() > 0) {
     ensemble = combinaison(ensemble);
     ensemble = checkMins(ensemble);
     afficherListe(ensemble);
     }
     }*/
    public static void main(String[] args) {
        //Emplacement fichier
        final String donnees = "src/tp1/donnees";
        //Lecture du fichier
        String jeuDonnees = null;
        try {
            jeuDonnees = Readfile.readFileQuick(donnees, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(e);
        }
        /**
         * ****************************************************************
         */
        Main algo = new Main();

        /*Bon format de données*/
        ArrayList<ArrayList<Apriori>> jeuDonneesFormat = algo.stringToCorrectFormat(jeuDonnees);
        ArrayList<ArrayList<Apriori>> sauvegardePremierTreillis = algo.stringToCorrectFormat(jeuDonnees);
        algo.compterItems(jeuDonneesFormat);
        algo.afficherTreeMap(treilli);
        sauvegardePremierTreillis = algo.checkMins(jeuDonneesFormat);
        System.out.println("APRES ELAGAGE");
        algo.afficherTreeMap(treilli);
        jeuDonneesFormat = algo.combinaison();
        algo.afficherListe(jeuDonneesFormat);

        algo.compterItems(jeuDonneesFormat);

        //algo.afficherListe(algo.combinaison(donneesList));
    }
}
