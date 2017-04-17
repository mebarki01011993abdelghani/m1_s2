package ia_1;

import java.util.Scanner;

public class PostFixTest {

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        Scanner input = new Scanner(System.in);
        Node theTree;
        System.out.println("Enter the expression that you want to convert to prefix.");
        String infix = input.next();
        System.out.println("Your expression is : " + infix);
        String postfix = PostFixConverter.infix2prefix(infix);
        System.out.println("Postfix is : " + postfix);
        String negative = PostFixConverter.negative(postfix);
        System.out.println("Negation is : " + negative);
        String simplify = PostFixConverter.simplifyNegative(negative);
        System.out.println("Simplify formula : " + simplify);
        theTree = BinaryTree.buildTree(simplify);
        BinaryTree.inOrderTraverseTree(theTree);

    }

}
