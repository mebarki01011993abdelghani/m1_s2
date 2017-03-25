/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.sun.corba.se.impl.orbutil.graph.Node;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author scra
 */
public class Test  implements Node{
    AtomicReference<Node> ref = new AtomicReference<Node>();

    
    public void push(Integer value){
        Node o = new Node(value);
        Node actuel;
        do{
            actuelHaut = haut.get();
            nouveauHaut.suivant = actuelHaut;
        }while(!haut.compareAndSet(actuelHaut,nouveauHaut));
        
    }

    

}
