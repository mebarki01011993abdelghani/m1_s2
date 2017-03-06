package tp4;

// -*- coding: utf-8 -*-
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import static tp4.LineExecutorCompletion.ecs;
import static tp4.LineExecutorCompletion.executorService;

public class TriRapide implements Callable<int[]> {

    static final int taille = 1_000_000;                   // Longueur du tableau à trier
    static final int[] tableau = new int[taille];         // Le tableau d'entiers à trier 
    static final int borne = 10 * taille;                  // Valeur maximale dans le tableau
    static ExecutorService executorService;
    static CompletionService<int[]> ecs;

    private int[] tableauATrier;
    private int debut;
    private int fin;

    public TriRapide(int[] tableauATrier, int debut, int fin) {
        this.tableauATrier = tableauATrier;
        this.debut = debut;
        this.fin = fin;
    }

    public int[] getTableauATrier() {
        return tableauATrier;
    }

    public void setTableauATrier(int[] tableauATrier) {
        this.tableauATrier = tableauATrier;
    }

    public int getDebut() {
        return debut;
    }

    public void setDebut(int debut) {
        this.debut = debut;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    private static void echangerElements(int[] t, int m, int n) {
        int temp = t[m];
        t[m] = t[n];
        t[n] = temp;
    }

    private static int partitionner(int[] t, int début, int fin) {
        int v = t[fin];                               // Choix (arbitraire) du pivot : t[fin]
        int place = début;                            // Place du pivot, à droite des éléments déplacés
        for (int i = début; i < fin; i++) {            // Parcours du *reste* du tableau
            if (t[i] < v) {                            // Cette valeur t[i] doit être à droite du pivot
                echangerElements(t, i, place);        // On le place à sa place
                place++;                              // On met à jour la place du pivot
            }
        }
        echangerElements(t, place, fin);              // Placement définitif du pivot
        return place;
    }

    private static void trierRapidement(int[] t, int début, int fin) {
        if (début < fin) {                             // S'il y a un seul élément, il n'y a rien à faire!
            int p = partitionner(t, début, fin);
            int unCentieme = taille / 100;              // un centieme de la taille du tableau
            if (t.length > 1000 && t.length > unCentieme) {
                try {
                    // si supérieur à un centieme et taille du tableau courant est plus gros que 1000
                    ecs.submit(new TriRapide(t, p, p - 1));
                    int[]a = ecs.take().get();

                    ecs.submit(new TriRapide(t, p + 1, fin));
                    t = ecs.take().get();
                    

                } catch (InterruptedException ex) {
                    Logger.getLogger(TriRapide.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(TriRapide.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                trierRapidement(t, début, p - 1);
                trierRapidement(t, p + 1, fin);
            }
        }
    }

    private static void afficher(int[] t, int début, int fin) {
        for (int i = début; i <= début + 3; i++) {
            System.out.print(" " + t[i]);
        }
        System.out.print("...");
        for (int i = fin - 3; i <= fin; i++) {
            System.out.print(" " + t[i]);
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {

        /*
         Initialisation du Pool de threads
         */
        executorService = Executors.newFixedThreadPool(4);
        ecs = new ExecutorCompletionService<int[]>(executorService);

        Random alea = new Random(System.currentTimeMillis());
        for (int i = 0; i < taille; i++) {                          // Remplissage aléatoire du tableau
            tableau[i] = alea.nextInt(2 * borne) - borne;
        }
        System.out.print("Tableau initial : ");
        afficher(tableau, 0, taille - 1);                         // Affiche le tableau à trier

        System.out.println("Démarrage du tri rapide.");
        int[] tableauATrier = new int[taille];
        for (int i = 0; i < taille; i++) {                          // Copie du tableau aléatoire
            tableauATrier[i] = tableau[i];
        }

        // On construit le tableau a trier
        long démarrage = System.nanoTime();
        trierRapidement(tableauATrier, 0, taille - 1);             // Tri du tableau
        long terminaison = System.nanoTime();
        long durée = (terminaison - démarrage) / 1_000_000;

        System.out.print("Tableau trié : ");
        afficher(tableauATrier, 0, taille - 1);                   // Affiche le tableau obtenu
        System.out.println("obtenu en " + durée + " millisecondes.");
        executorService.shutdown();

    }

    @Override
    public int[] call() throws Exception {
        trierRapidement(this.getTableauATrier(), this.debut, this.fin);             // Tri du tableau
        return this.getTableauATrier(); // on retourne la taille du tableau
    }
}


/*
 $ make
 javac *.java
 $ java TriRapide
 Tableau initial :  4967518 -8221265 -951337 4043143... -4807623 -1976577 -2776352 -6800164
 Démarrage du tri rapide.
 Tableau trié :  -9999981 -9999967 -9999957 -9999910... 9999903 9999914 9999947 9999964
 obtenu en 85 millisecondes.
 */
