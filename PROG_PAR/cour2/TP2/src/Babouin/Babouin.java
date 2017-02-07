
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// -*- coding: utf-8 -*-
enum Cote {

    EST, OUEST
}                   // Le canyon possède un côté EST et un côté OUEST

class Babouin extends Thread {

    private static int numeroSuivant = 0;    // Compteur partagé par tous les babouins
    private int numero;                      // Numéro du babouin
    private Corde corde;                     // Corde utilisée par le babouin
    private Cote origine;                    // Côté du canyon où apparaît le babouin: EST ou OUEST

    Babouin(Corde corde, Cote origine) {      // Constructeur de la classe Babouin
        this.corde = corde;                    // Chaque babouin peut utiliser la corde
        this.origine = origine;                // Chaque babouin apparaît d'un côté précis du canyon
        numero = ++numeroSuivant;              // Chaque babouin possède un numéro distinct
    }

    public int getNumero() {
        return this.numero;
    }

    public void run() {
        System.out.println("Le babouin " + numero + " arrive sur le côté " + origine + " du canyon.");
        corde.saisir(origine);                 // Pour traverser, le babouin saisit la corde
        System.out.println("Le babouin " + numero
                + " commence à traverser sur la corde en partant de l'" + origine + ".");
        try {
            sleep(5000);
        } catch (InterruptedException e) {
        } // La traversée ne dure que 5 secondes
        System.out.println("Le babouin " + numero + " a terminé sa traversée.");
        corde.lacher(origine);                 // Arrivé de l'autre côté, le babouin lâche la corde
        System.out.println("Le babouin " + numero + " a lâché la corde et s'en va.");
    }

    public static void main(String[] args) {
        Corde corde = new Corde();    // La corde relie les deux côtés du canyon
        for (int i = 1; i < 20; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            if (Math.random() >= 0.5) {
                new Babouin(corde, Cote.EST).start();    // Création d'un babouin à l'est du canyon
            } else {
                new Babouin(corde, Cote.OUEST).start();  // Création d'un babouin à l'ouest du canyon
            }
        } // Une vingtaine de babouins sont répartis sur les deux côtés du canyon
    }
}

class Corde {

    private ArrayList<Babouin> babouins;
    // Représente les babouins sur la corde,// on a pas besoin du mot clef statique car il y a seulement une seule corde
    private Cote cote;// Cote du premier babouins passant sur la corde
    private int capacite;

    public Corde() {
        this.babouins = new ArrayList<Babouin>(); // les babouins sur la corde
        this.cote = null; // le cote du premier babouin
        this.capacite = 5; // capacite max de la corde
    }

    public void afficherCorde() {
<<<<<<< HEAD
        synchronized (Corde.class) {
            System.out.println("Corde : ");

            for (int i = 0; i < etatCorde.size(); i++) {
                System.out.println(etatCorde.get(i).getName());
            }
            System.out.println("----------------------");
=======
        System.out.println("Corde : ");

        for (int i = 0; i < babouins.size(); i++) {
            Babouin bab = (Babouin) babouins.get(i);
            System.out.println(bab.getNumero());
>>>>>>> refs/remotes/origin/master
        }
    }

<<<<<<< HEAD
    public void saisir(Cote origine) {

        while (etatCorde.size() == 5 || origine != cote) {
            // on fait attendre les babouins

            if (etatCorde.size() == 0) {
                cote = origine;
            }

=======
    public synchronized void saisir(Cote origine) {
        if (cote == null || babouins.size() == 0) {
            cote = origine;
        }
        while (cote != origine || babouins.size() == capacite) {
>>>>>>> refs/remotes/origin/master
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
<<<<<<< HEAD
        synchronized (Corde.class) {

            if (etatCorde.size() == 0) { // il monte car pas de babouin sur la corde
                cote = origine;
                etatCorde.add(Thread.currentThread());
            } else {
                etatCorde.add(Thread.currentThread());
            }
            afficherCorde();
        }
    }

    public void lacher(Cote origine) {

        //Tant que le babouin n'est pas la premier etre monté sur la corde
        while (etatCorde.get(0) != Thread.currentThread()) {
=======
        Babouin babtwo = (Babouin) Thread.currentThread();
        babouins.add(babtwo);
        afficherCorde();
    }

    public synchronized void lacher(Cote origine) {
        while (babouins.get(0) != (Babouin) Thread.currentThread() || cote != origine) {
>>>>>>> refs/remotes/origin/master
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
<<<<<<< HEAD
        synchronized (Corde.class) {

            etatCorde.remove(Thread.currentThread());
            afficherCorde();

            notifyAll();
        }

=======
        babouins.remove((Babouin) Thread.currentThread());
        notifyAll();
>>>>>>> refs/remotes/origin/master
    }
}
