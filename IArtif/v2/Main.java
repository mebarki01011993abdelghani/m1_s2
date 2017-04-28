/**
 *
 * Main class du Programme, Orcherstre tout le programme.
 * Recupere l'input, et le traite de diverse façons.
 *
 * */
package ia_1;

import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws Exception {
        BinaryTree tree = new BinaryTree();
        Prefixer formule;
        System.out.println("Enter the expression that you want to convert to prefix.");
        Scanner input = new Scanner(System.in);
        //String formuleBrut ="(-p&q)|(p|-q)";
        String formuleBrut = input.next();
        formule = new Prefixer(formuleBrut); //-((-(MA|MB))>(M(A|B))) //-((-((LA&B)|(A&B)))>(L(A|B)))
        String infix = formule.getInfixFormula();
        System.out.println("Your expression Infix       is : " + infix);
        String prefix = formule.getPrefixFormula();
        System.out.println("Your expression Prefix      is : " + prefix);
        String negative = formule.getNegativeFormula();
        System.out.println("Your expression Negativ     is : " + negative);
        String simplify = Formula.simplifyNegative(negative);
        System.out.println("Your expression simplified  is : " + simplify);
        Node root = BinaryTree.initTree(simplify);
        System.out.println("Arbre :");
        TreePrinters.print(root);
        ClosingBranch test = new ClosingBranch();
        ClosingBranch.testTree();
        //TreePrinters.print(test);
    }
}
