package ia_1;

import static ia_1.PostFixConverter.infix2prefix;
import static ia_1.PostFixConverter.negative;

import java.util.Scanner;

public class PostFixTest {

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        Scanner input = new Scanner(System.in);
        BinaryTree theTree = new BinaryTree();
        System.out.println("Enter the expression that you want to convert to prefix.");
        String infix = input.next();
        System.out.println("Your expression is : " + infix);
        System.out.println("Prefix is : " + infix2prefix(infix));
        System.out.println("Negation is : " + negative(infix2prefix(infix)));
    }

}
