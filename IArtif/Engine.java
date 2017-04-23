/**
 * 
 * Main class du Programme, Orcherstre tout le programme.
 * Recupere l'input, et le traite de diverse façons.
 * 
 **/
package ia_1;

import java.util.Scanner;

public class Engine {

    public static void main(String args[]) throws Exception {
        Scanner input = new Scanner(System.in);


        System.out.println("Enter the expression that you want to convert to prefix.");
        String infix = input.next();
        System.out.println("Your expression is : " + infix);
        String postfix = Formula.infix2prefix(infix);
        System.out.println("Postfix is : " + postfix);
        String negative = Formula.negative(postfix);
        System.out.println("Negation is : " + negative);
        String simplify = Formula.simplifyNegative(negative);
        System.out.println("Simplify formula : " + simplify);
        String[] test = new String[2];
        test = Formula.developedFormula(simplify);
        System.out.println(test[0]);
        System.out.println(test[1]);
    }
}

