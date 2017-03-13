/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.locks.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * @author scra
 */
public class Test extends Thread {

    static int compteur = 10;
    private static final CyclicBarrier cmpt = new CyclicBarrier(compteur);
    private static final Thread[] ths = new Thread[10];

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            ths[i] = new Test();
            ths[i] .start();
        }
        for (int i = 0; i < 10; i++) {
            ths[i].join();
        }

        System.out.println("DEVRAIT AFFCIHER 0 => " + cmpt.getNumberWaiting());
    }

    public void run() {
        try {
            System.out.println(cmpt.getNumberWaiting() + " " + currentThread().getName());
            System.out.println("JE MENDORS");
            cmpt.await();
            sleep(20);
        } catch (InterruptedException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BrokenBarrierException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("REVEILLER");

        System.out.println("Terminer " + currentThread().getName());
    }

}
