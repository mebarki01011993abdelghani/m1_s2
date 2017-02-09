package tpc.Association_de_nains_qui_dansent;

// -*- coding: utf-8 -*-
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeptNains {

    public static void main(String[] args) {
        int nbNains = 7;
        BlancheNeige bn = new BlancheNeige();
        String nom[] = {"Simplet", "Dormeur", "Atchoum", "Joyeux", "Grincheux", "Prof", "Timide"};
        Nain nain[] = new Nain[nbNains];
        for (int i = 0; i < nbNains; i++) {
            nain[i] = new Nain(nom[i], bn);
            nain[i].start();
        }
    }
}

class Nain extends Thread {

    public BlancheNeige bn;
    public int reveil;

    public Nain(String nom, BlancheNeige bn) {
        this.setName(nom);
        this.bn = bn;
    }

    public void run() {
        Random alea = new Random();
        this.reveil = 1000 + alea.nextInt(5000);
        try {
            sleep(reveil);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getName() + " veut danser avec Blanche-Neige.");
        bn.sAssocier();
        System.out.println(getName() + " est maintenant associé à Blanche-Neige.");
        bn.danser();
        System.out.println(getName() + " a commencé de danser avec un autre nain.");
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getName() + " a fini de danser: il va se séparer.");
        bn.seSeparer();
    }
}

class BlancheNeige {

    private volatile ArrayList<String> dancer; // Il y a deux danceurs dans les bras de blanche neige
    private volatile boolean occupe; // Blanche neige dance si true sinon false

    public BlancheNeige() {
        this.dancer = new ArrayList<String>();
        this.occupe = false;
    }

    public ArrayList<String> getDancer() {
        return dancer;
    }

    public void setDancer(ArrayList<String> dancer) {
        this.dancer = dancer;
    }

    public boolean isOccupe() {
        return occupe;
    }

    public void setOccupe(boolean occupe) {
        this.occupe = occupe;
    }

    public synchronized void sAssocier() {
        while (occupe == true) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(BlancheNeige.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        // on l'ajoute dans les bras de blanche neige
        Nain n1 = (Nain) Thread.currentThread();
        if (dancer.size() == 0) {
            dancer.add(n1.getName()); // le nain est avec blanche neige
        } else {
            dancer.add(n1.getName()); // le nain est avec blanche neige
            occupe = true; // nous avons deux nains
        }
    }

    public synchronized void danser() {
        // on attend un second nain
        while (occupe == false) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(BlancheNeige.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public synchronized void seSeparer() {
        Nain n1 = (Nain) Thread.currentThread();
        if (dancer.size() == 2) {
            dancer.remove(n1.getName()); // le nain est avec blanche neige
        } else {
            dancer.remove(n1.getName()); // le nain est avec blanche neige
            occupe = false;
        }
        notifyAll();
    }
}
