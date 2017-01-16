/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author scra
 */
public class Main {

    public static HashMap<String, Integer> treilli;
    public static int min;
    /*METHODES*/
    /*Convertit un treilli au format string en un arrayList*/

    public ArrayList<Apriori> stringToArrayList(String modele) {
        ArrayList<Apriori> liste = new ArrayList<Apriori>();
        /*Décomposition*/
        String[] lignes = null;
        /**
         * ***********************************
         */
        // SPLIT
        /**
         * *******************************
         */
        int nbrsLigne = lignes.length;

        for (int i = 0; i < nbrsLigne; i++) {
            String[] vals = lignes[i].split("");// A REMPLIR
            Apriori a = new Apriori(vals[0], Integer.parseInt(vals[1]));
            liste.add(a);
        }

        return liste;
    }

    /*Compte le nombre d'item dans un treilli*/
    public void compterItems(ArrayList<Apriori> liste) {
        int taille = liste.size();
        int count = 0;
        for (int i = 0; i < taille; i++) {
            String ite = liste.get(i).getItem();
            if (treilli.containsKey(ite)) {
                count = treilli.get(ite) + 1;
                treilli.put(ite, count);
            } else {
                treilli.put(ite, 1);
            }
        }
    }

    /*afficher arrayList*/
    public void afficherListe(ArrayList<Apriori> liste) {
        for (Iterator<Apriori> iterator = liste.iterator(); iterator.hasNext();) {
            Apriori next = iterator.next();
            System.out.print("ITEM : " + next.getItem() + " -- Occurence " + next.getOccurence() + "\n");
        }

    }
    /*Générer combinaison*/

    public ArrayList<Apriori> combinaison(ArrayList<Apriori> donnees) {
        ArrayList<Apriori> donneesLn_1 = new ArrayList<Apriori>();

        if (donnees.get(0).getItem().length() == 1) {
            donneesLn_1 = combinaisonTailleUne(donnees);
        } else {
            donneesLn_1 = combinaisonTailleN(donnees);
        }
        return donneesLn_1;

    }

    /*Combinaison de taille une*/
    private static ArrayList<Apriori> combinaisonTailleUne(ArrayList<Apriori> donnees) {

        ArrayList<Apriori> donneesLn = new ArrayList<Apriori>();
        // Si les données sont de taille "1"
        for (int i = 0; i < donnees.size() - 1; i++) {
            String firstItem = donnees.get(i).getItem();
            for (int j = i + 1; j < donnees.size(); j++) {
                String nextItem = donnees.get(j).getItem();
                /*Génération de la nouvelle données*/
                String result = firstItem.concat(nextItem);
                Apriori obj = new Apriori(result);
                //add
                donneesLn.add(obj);
            }

        }
        return donneesLn;
    }

    /*Combinaison de taille N différent de 1*/
    private static ArrayList<Apriori> combinaisonTailleN(ArrayList<Apriori> donnees) {
        ArrayList<Apriori> donneesLn = new ArrayList<Apriori>();
        boolean find = false;
        // Si les données sont de taille "1"
        for (int i = 0; i < donnees.size() - 1; i++) {
            String firstItem = donnees.get(i).getItem();
            /*Récupérer la deniere lettre*/
            char lastLetter = firstItem.toCharArray()[firstItem.length() - 1];
            for (int j = i + 1; j < donnees.size(); j++) {
                String nextItem = donnees.get(j).getItem();
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
                    donneesLn.add(obj);
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
    public static ArrayList<Apriori> checkMins(ArrayList<Apriori> ensemble) {
        int tailleEnsemble = ensemble.size();
        for (int i = 0; i < tailleEnsemble; i++) {
            Apriori it = ensemble.get(i);
            int nbs = treilli.get(it.getItem());
            if (nbs < min) {
                ensemble.remove(i);
            } else {
                // On set le nombre d'occurences
                ensemble.get(i).setOccurence(nbs);
            }
        }
        return ensemble;
    }

    public void aprioriAlgo(ArrayList<Apriori> ensemble) {
        ensemble = checkMins(ensemble);
        afficherListe(ensemble);

        while (ensemble.size() > 0) {
            ensemble = combinaison(ensemble);
            ensemble = checkMins(ensemble);
            afficherListe(ensemble);
        }
    }

    public static void main(String[] args) {
        //Emplacement fichier
        final String donnees = "src/tp1/donnees";
        //Lecture du fichier
        try {
            String jeuDonnees = Readfile.readFileQuick(donnees, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(e);
        }
        /**
         * ****************************************************************
         */
        Main algo = new Main();
        ArrayList<Apriori> donneesList = new ArrayList<Apriori>();

        Apriori a = new Apriori("ABC", 10);
        Apriori b = new Apriori("CPF", 8);
        Apriori c = new Apriori("CDF", 1);
        Apriori d = new Apriori("FDE", 5);
        Apriori e = new Apriori("FDI", 4);
        Apriori f = new Apriori("IOP", 4);

        donneesList.add(a);
        donneesList.add(b);
        donneesList.add(c);
        donneesList.add(d);
        donneesList.add(e);
        donneesList.add(f);

        algo.afficherListe(algo.combinaison(donneesList));

    }
}
