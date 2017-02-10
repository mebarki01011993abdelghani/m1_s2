
import java.util.logging.Level;
import java.util.logging.Logger;

// -*- coding: utf-8 -*-
public class Diner {

    public static void main(String args[]) {
        int nbSauvages = 10;               // La tribu comporte 10 sauvages affamés
        int nbPortions = 5;                // Le pôt contient 5 parts, lorsqu'il est rempli
	/*
         try {
         nbSauvages = Integer.parseInt(args[0]) ;
         nbPortions = Integer.parseInt(args[1]) ; }
         catch(Exception e) {
         System.err.println("Usage: java Diner <nb de sauvages> <taille du pot>") ;
         System.exit(1) ;
         }
         */
        System.out.println("Il y a " + nbSauvages + " sauvages.");
        System.out.println("Le pôt peut contenir " + nbPortions + " portions.");
        Pot pot = new Pot(nbPortions);
        new Cuisinier(pot).start();
        for (int i = 0; i < nbSauvages; i++) {
            new Sauvage(pot).start();
        }
    }
}

class Sauvage extends Thread {

    public Pot pot;

    public Sauvage(Pot pot) {
        this.pot = pot;
    }

    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " : " + "J'ai faim!");
            pot.seServir();
            System.out.println(Thread.currentThread().getName() + " : " + "Je me suis servi et je vais manger!");
        }
    }
}

class Cuisinier extends Thread {

    public Pot pot;

    public Cuisinier(Pot pot) {
        this.pot = pot;
    }

    public void run() {
        while (true) {
            System.out.println("Cuisinier: Je suis endormi.");
            pot.remplir();
        }
    }
}

class Pot {

    private volatile int nbPortions;
    private int nbPortionsMax;
    private volatile boolean wakeUp;

    public Pot(int nbPortions) {
        this.nbPortions = nbPortions;
        this.nbPortionsMax = nbPortions;
        this.wakeUp = false;
    }

    public int getNbPortions() {
        return nbPortions;
    }

    public void setNbPortions(int nbPortions) {
        this.nbPortions = nbPortions;
    }

    public synchronized void remplir() {
        while (wakeUp == false || nbPortions > 0) { // on attend que le plat soit vide
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Pot.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        System.out.println("Cuisinier : Je suis reveillé !");
        System.out.println("Je cuisine !");
        nbPortions = nbPortionsMax;
        System.out.println("REGALEZ VOUS : pot plein !!");

        notifyAll();
    }

    public synchronized void seServir() {
        System.out.println(Thread.currentThread().getName() + " : " + "Je prends la louche ");

        while (nbPortions == 0) {   // on attend que le plat soit à de nouveau plein         
            try {
                if (wakeUp == false) {
                    System.out.println("Le pot est vide !");
                    System.out.println(Thread.currentThread().getName() + " : " + "je reveille le cuisinier ");
                    wakeUp = true;
                    System.out.println(Thread.currentThread().getName() + " : " + "J'attends que le pôt soit plein ");
                }
                notifyAll();
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Pot.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        wakeUp = false;
        System.out.println("Il y a une part de disponible ");
        nbPortions = nbPortions - 1;
        System.out.println(Thread.currentThread().getName() + " : " + "Je pose la louche ");
        notifyAll();
    }
}
