/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp4;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static tp4.Mandelbrot.taille;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.org.mozilla.javascript.internal.ObjArray;

/**
 *
 * @author scra
 */
public class LineExecutorCompletion implements Callable<Integer> {

    //private final Lock indiceLock = new ReentrantLock();
    // static volatile int indiceL = 0;  // permet de connaitre les lignes restantes a caculer
    int indiceX; // indice de la ligne
    int indiceY; // taille de la ligne
    //static CountDownLatch latch;
    static Object lock = new Object();
    static volatile int incr = 0;
    static final int nbThreads = 4; // nombre de threads
    static ExecutorService executorService;
    static CompletionService<Integer> ecs;

    public LineExecutorCompletion(int x) {
        this.indiceY = Mandelbrot.taille;
        this.indiceX = x;
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


    private void calculerLigne(LineExecutorCompletion ligne) {
        for (int i = 0; i < ligne.getIndiceY(); i++) {
            Mandelbrot.colorierPixel(ligne.getIndiceX(), i);
        }
    }

    //Méthode d'abstraction pour la classe Mandelbrot, elle calcule l'ensemble des lignes
    public static void calculerLignes() {
        buildExecutor();
    }

    //Construit le réservoir de threads
    private static void buildExecutor() {
        //Un pool de threads permet de contenir un ensemble de threads qui pourront être utilisés pour exécuter des tâches. 
        //Les pools de threads sont particulièrement utiles pour exécuter des tâches similaires et indépendantes.
        executorService = Executors.newFixedThreadPool(4);
        ecs = new ExecutorCompletionService<Integer>(executorService);

        for (int i = 0; i < Mandelbrot.taille; i++) {
            ecs.submit(new LineExecutorCompletion(i));
        }
        
        executorService.shutdown();

        for (int i = 0; i < Mandelbrot.taille; i++) {
            Integer resultat;
            try {
                ecs.take();
            } catch (InterruptedException ex) {
                Logger.getLogger(LineExecutorCompletion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /*
         Les threads du pool ne sont pas des démons : si la méthode shutdown() n'
         est pas invoquée alors la JVM continuera indéfiniment de s'exécuter même si 
         les traitements du thread principal sont terminés.
         */
    }

    @Override
    public Integer call() throws Exception {
        calculerLigne(this);
        return this.indiceX;
    }

}
