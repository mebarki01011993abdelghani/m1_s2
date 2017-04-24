package ia_1;

import java.util.ArrayList;

public class BinaryTree {

    public static void inOrderTraverseTree(Node focusNode) {
        if (focusNode != null) {
            inOrderTraverseTree(focusNode.leftChild);
            System.out.println(focusNode.getName());
            inOrderTraverseTree(focusNode.rightChild);
        }
    }

    public static Rules findRules(Node focusNode) {
        switch (focusNode.getName()) {
            case "&":
                System.out.println("");
                return Rules.AND(focusNode);
            case "-&":
                System.out.println("");
                return Rules.NAND(focusNode);
            case "|":
                System.out.println("");
                return Rules.OR(focusNode);
            case "-|":
                System.out.println("");
                return Rules.NOR(focusNode);
            case ">":
                System.out.println("");
                return Rules.IMPLY(focusNode);
            case "->":
                System.out.println("");
                return Rules.Nimply(focusNode);
            case "-L":
                System.out.println("");
                return Rules.Ncertitude(focusNode);
            case "L":
                System.err.println("No rules but keep it");
                return new Rules();
            default:
                System.out.println("No rules");
                return null;
        }
    }

    public static Node initTree(String formula) {
        Node nodeRoot = new Node("");
        buildTree(formula, nodeRoot);
        return nodeRoot;
    }

    public static String buildTree(String formule, Node focusNode) {
        String[] result;
        result = Formula.getNextNodeName(formule);
        String nameNode = result[0];
        formule = result[1];

        if (nameNode.endsWith("&") || nameNode.endsWith("|") || nameNode.endsWith(">")) {

            focusNode.setName(nameNode);

            result = Formula.getNextNodeName(formule);

            focusNode.setLeftChild(new Node(result[0]));
            if (result[0].endsWith("&") || result[0].endsWith("|") || result[0].endsWith(">")) {

                formule = buildTree(result[1], focusNode.getLeftChild());
            } else {
                formule = result[1];

            }
            result = Formula.getNextNodeName(formule);
            focusNode.setRightChild(new Node(result[0]));

            if (result[0].endsWith("&") || result[0].endsWith("|") || result[0].endsWith(">")) {
                formule = buildTree(result[1], focusNode.getRightChild());
            } else {
                formule = result[1];
            }
            formule = result[1];

        } else {
            focusNode.setLeftChild(new Node(nameNode));//B
            result = Formula.getNextNodeName(formule);
            focusNode.setRightChild(new Node(result[0]));
            formule = result[1];
        }
        return formule;
    }

}
