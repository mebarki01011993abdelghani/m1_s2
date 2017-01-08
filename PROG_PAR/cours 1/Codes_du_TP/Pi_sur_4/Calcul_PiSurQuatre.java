// -*- coding: utf-8 -*-

import java.lang.Math; 

public class Calcul_PiSurQuatre{
  public static void main(String[] args) {
      int nombreDeTirages = 1000 ;
      int tiragesDansLeDisque = 0 ;
      double x, y, resultat ;
      
      if (args.length>0) {
	  try { nombreDeTirages = Integer.parseInt(args[0]); } 
	  catch(NumberFormatException e) { 
	      System.err.println 
	      ("Usage : java Calcul_PiSurQuatre <nb de tirages>"); 
	      System.exit(1); 
	  }
      }
      for (int i = 0; i < nombreDeTirages; i++) {
	  x = Math.random() ;
	  y = Math.random() ;
	  if (x * x + y * y <= 1) tiragesDansLeDisque++ ;
      }
      resultat = (double) tiragesDansLeDisque / nombreDeTirages ;
      System.out.println("Estimation de Pi/4: " + resultat) ;
      System.out.println("Pourcentage d'erreur: "
			 + 100 * Math.abs(resultat-Math.PI/4)/(Math.PI/4)
			 + " %");
  }
}

/*
> javac Calcul_PiSurQuatre.java
> java Calcul_PiSurQuatre
Estimation de Pi/4: 0.797
Pourcentage d'erreur: 1.4771917153924754 %
> java Calcul_PiSurQuatre 1000000
Estimation de Pi/4: 0.785849
Pourcentage d'erreur: 0.05740229905829259 %
>
*/
