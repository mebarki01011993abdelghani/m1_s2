package tpc.Pot_des_sauvages;

import java.util.logging.Level;
import java.util.logging.Logger;

// -*- coding: utf-8 -*-
public class Diner {

    public static void main(String args[]) {
        int nbSauvages = 10;               // La tribu comporte 10 sauvages affamés
        int nbPortions = 5;                // Le pôt contient 5 parts, lorsqu'il est rempli

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

    private volatile int nbPortions; // nombre de portions du pot restante
    private int nbPortionsMax; // nombre de portions que peut contenir un pot
    private volatile boolean wakeUp; // boolean réveillant le cuisinier
    private static volatile Sauvage louche; // permet de savoir qui a la louche

    //Constructeur du pot
    public Pot(int nbPortions) {
        this.nbPortions = nbPortions;
        this.nbPortionsMax = nbPortions;
        this.wakeUp = false;
    }

    //GET AND SET
    public int getNbPortions() {
        return nbPortions;
    }

    public void setNbPortions(int nbPortions) {
        this.nbPortions = nbPortions;
    }

    // Remplir est la procédure du cuisinier qui permet de remplir de le pot quand le cuisinier est réveillé et que le pot soit vide
    public synchronized void remplir() {
        while (!wakeUp || nbPortions > 0) { // on attend que le plat soit vide
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

        // On réveil les sauvages
        notifyAll();
    }

    //Procédure permettant aux sauvages de se servir.Ils peuvent réveiller le cuisinier si le pot est vide.
    public synchronized void seServir() {

        // le sauvage attend que la louche soit disponible et qu'une portion soit disponlible
        while (nbPortions == 0 || louche != (Sauvage) Thread.currentThread()) {         
            try {
                // si la louche n'est pas prise
                if (louche == null) {
                    // le sauvage a la louche seulement lui peut se servir et réveiller le cuisinier
                    louche = (Sauvage) Thread.currentThread();
                    System.out.println(Thread.currentThread().getName() + " : " + "Je prends la louche ");

                    //On réveil le cuisinier une seul fois et il faut que le pot soit toujours vide
                    if (wakeUp == false && nbPortions == 0) {
                        louche = (Sauvage) Thread.currentThread();
                        System.out.println("Le pot est vide !");
                        System.out.println(Thread.currentThread().getName() + " : " + "je reveille le cuisinier ");
                        // réveiller le cuisinier
                        wakeUp = true;
                        System.out.println(Thread.currentThread().getName() + " : " + "J'attends que le pôt soit plein ");
                    }
                }
                //Ici on réveil les sauvage et potentiellement le cuisinier si wakeUp est à true
                notifyAll();
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Pot.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        wakeUp = false;
        System.out.println("Il y a une part de disponible ");
        //On perd une portion
        nbPortions = nbPortions - 1;
        System.out.println("nombre de portions : " + nbPortions);
        System.out.println(Thread.currentThread().getName() + " : " + "Je pose la louche ");
        // Le sauvage perd la louche un autre peut se servir
        louche = null;
        notifyAll();
    }
}
