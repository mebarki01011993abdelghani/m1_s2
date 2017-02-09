// -*- coding: utf-8 -*-

public class Diner {
    public static void main(String args[]) {
	int nbSauvages = 10 ;               // La tribu comporte 10 sauvages affamés
	int nbPortions = 5 ;                // Le pôt contient 5 parts, lorsqu'il est rempli
	/*
	  try {
	  nbSauvages = Integer.parseInt(args[0]) ;
	  nbPortions = Integer.parseInt(args[1]) ; }
	  catch(Exception e) {
	  System.err.println("Usage: java Diner <nb de sauvages> <taille du pot>") ;
	  System.exit(1) ;
	  }
	*/
	System.out.println("Il y a " + nbSauvages + " sauvages.") ;
	System.out.println("Le pôt peut contenir "+ nbPortions + " portions.") ;
	Pot pot = new Pot(nbPortions) ;
	new Cuisinier(pot).start() ;
	for (int i = 0 ; i < nbSauvages ; i++) {
	    new Sauvage(pot).start() ;
	}
    }
}  


class Sauvage extends Thread{
    public Pot pot ;
    public Sauvage(Pot pot){
	this.pot = pot ;
    }
    public void run(){
	while(true){
	    System.out.println(getName() + ": J'ai faim!") ;
	    pot.seServir() ;
	    System.out.println(getName() + ": Je me suis servi et je vais manger!") ;
	}
    }
}	

class Cuisinier extends Thread {
    public Pot pot ;
    public Cuisinier(Pot pot) {
	this.pot = pot ;
    }
    public void run(){
	while(true){
	    System.out.println("Cuisinier: Je suis endormi.") ;
	    pot.remplir() ;
	}
    }
}	

class Pot {
    ...
}

