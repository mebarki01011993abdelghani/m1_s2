/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.concurrent.CompletionService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static tp4.Mandelbrot.taille;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author scra
 */
public class LineExecutorLoquet implements Runnable {

    //private final Lock indiceLock = new ReentrantLock();
    // static volatile int indiceL = 0;  // permet de connaitre les lignes restantes a caculer
    int indiceX; // indice de la ligne
    int indiceY; // taille de la ligne
    static CountDownLatch latch;
    static final int nbThreads = 4; // nombre de threads
    static ExecutorService executorService;
    static CompletionService<Long> ecs;

    private LineExecutorLoquet(CountDownLatch latch) {
        this.indiceY = Mandelbrot.taille;
        this.latch = latch;
    }

    private LineExecutorLoquet(int indiceX, CountDownLatch latch) {
        this.indiceX = indiceX;
        this.indiceY = Mandelbrot.taille;
        this.latch = latch;
    }

    private int getIndiceX() {
        return indiceX;
    }

    private void setIndiceX(int indiceX) {
        this.indiceX = indiceX;
    }

    private int getIndiceY() {
        return indiceY;
    }

    private void setIndiceY(int indiceY) {
        this.indiceY = indiceY;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        while (this.getLatch().getCount() > 0) {
            this.setIndiceX((int) getLatch().getCount() - 1); // de 499 à 0
            this.getLatch().countDown();
            calculerLigne(this);
        }
    }

    /* public int getIndiceLigne() {
     indiceLock.lock();
     try {
     indiceL++;
     return indiceL;
     } finally {
     indiceLock.unlock();
     }
     }*/
    /*
     public int getIndiceLigne() {
     synchronized (lock) {
     indiceL++;
     return indiceL;
     }
     }*/
    private void calculerLigne(LineExecutorLoquet ligne) {
        for (int i = 0; i < ligne.getIndiceY(); i++) {
            System.out.println(ligne.getIndiceX() + " " + Thread.currentThread().getName());
            Mandelbrot.colorierPixel(ligne.getIndiceX(), i);
        }
    }

    //Méthode d'abstraction pour la classe Mandelbrot, elle calcule l'ensemble des lignes
    public static void calculerLignes() {
        buildExecutor();
        try {
            latch.await();  // Attend que le compteur soit à zero 
            System.out.println("CHARGEMENT DE MANDELBROT ...");
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    //Construit le réservoir de threads
    private static void buildExecutor() {
        executorService = Executors.newFixedThreadPool(nbThreads);
        ecs = new ExecutorCompletionService<Long>(executorService);
        latch = new CountDownLatch(Mandelbrot.taille);
        LineExecutorLoquet[] taches = new LineExecutorLoquet[nbThreads];
        for (int i = 0; i < nbThreads; i++) {
            taches[i] = new LineExecutorLoquet(latch);
            executorService.submit(taches[i]);
        }
        executorService.shutdown(); // on termine notre exector
    }

}
