// -*- coding: utf-8 -*-

import java.util.ArrayList;

public class SeptNains {

    public static void main(String[] args) {
        int nbNains = 7;
        BlancheNeige bn = new BlancheNeige();
        String nom[] = {"Simplet", "Dormeur", "Atchoum", "Joyeux",
            "Grincheux", "Prof", "Timide"};
        Nain nain[] = new Nain[nbNains];
        for (int i = 0; i < nbNains; i++) {
            nain[i] = new Nain(nom[i], bn);
        }
        for (int i = 0; i < nbNains; i++) {
            nain[i].start();
        }
    }
}

class BlancheNeige {

    private volatile boolean libre = true;
    private volatile ArrayList<Thread> fileAttente = new ArrayList<Thread>();

    // Initialement, Blanche-Neige est libre.
    public synchronized void requerir() {
        System.out.println(Thread.currentThread().getName()
                + " veut la ressource");
        fileAttente.add(Thread.currentThread());
        afficherFileAttente();
    }

    public synchronized void afficherFileAttente() {
        System.out.print("Etat file d'attente =>");
        for (int i = 0; i < fileAttente.size(); i++) {
            System.out.print("-" + fileAttente.get(i));
        }
        System.out.println("");
    }

    public synchronized void acceder() {
        while (!libre || fileAttente.get(0) != Thread.currentThread()) // Le nain s'endort sur le moniteur Blanche-Neige.
        {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        libre = false;
        System.out.println("\t" + Thread.currentThread().getName()
                + " accède à la ressource.");
        fileAttente.remove(Thread.currentThread());
        afficherFileAttente();

    }

    public synchronized void relacher() {
        System.out.println("\t\t" + Thread.currentThread().getName()
                + " relâche la ressource.");
        afficherFileAttente();
        libre = true;

        notifyAll();
    }
}

class Nain extends Thread {

    public BlancheNeige bn;

    public Nain(String nom, BlancheNeige bn) {
        this.setName(nom);
        this.bn = bn;
    }

    public void run() {
        int i = 0;
        while (true) {
            bn.requerir();
            bn.acceder();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bn.relacher();
            i++;
            System.out.println("i " + i);
        }
    }
}

/*
 $ javac -encoding ISO-8859-1 SeptNains.java
 $ java SeptNains 
 Simplet veut la ressource
 Simplet accède à la ressource.
 Atchoum veut la ressource
 Timide veut la ressource
 Joyeux veut la ressource
 Grincheux veut la ressource
 Dormeur veut la ressource
 Prof veut la ressource
 Simplet relâche la ressource.
 Simplet veut la ressource
 Simplet accède à la ressource.
 Simplet relâche la ressource.
 Simplet veut la ressource
 Simplet accède à la ressource.
 Simplet relâche la ressource.
 Simplet veut la ressource
 Simplet accède à la ressource.
 Simplet relâche la ressource.
 Simplet veut la ressource
 Simplet accède à la ressource.
 Simplet relâche la ressource.
 Simplet veut la ressource
 Simplet accède à la ressource.
 */
