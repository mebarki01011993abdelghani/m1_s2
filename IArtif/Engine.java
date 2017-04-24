/**
 *
 * Main class du Programme, Orcherstre tout le programme.
 * Recupere l'input, et le traite de diverse façons.
 *
 * */
package ia_1;

import java.util.Scanner;

public class Engine {

    public static void main(String args[]) throws Exception {
        BinaryTree tree = new BinaryTree();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the expression that you want to convert to prefix.");
        String formuleBrut = input.next();
        InfixToPrefix formule = new InfixToPrefix(formuleBrut);
        System.out.println("Your expression Infix       is : " + formule.infixFormula);
        System.out.println("Your expression Prefix      is : " + formule.prefixFormula);
        System.out.println("Your expression Negativ     is : " + formule.negativFormula);
        String simplify = Formula.simplifyNegative(formule.negativFormula);
        System.out.println("Your expression simplified  is : " + simplify);
        Node root = tree.initTree(simplify);

        /*
        String test = "DC&LALB";
        String truc[] = Formula.getNextNodeName(test);
        System.out.println("1 : " + truc[0] + " 2 :" + truc[1]);*/

    }
}
