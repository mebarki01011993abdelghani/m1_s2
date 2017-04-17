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

    public static Node buildTree(String formule) {

        ArrayList<Node> focusNodes = new ArrayList<>();

        int a = 1;
        //Initialisation
        String root = formule.substring(0, 1);
        if (root.equals("-")) {
            root = formule.substring(0, 2);
            a++;
        }

        Node nodeRoot = new Node(root);
        Node focusNode = nodeRoot;
        focusNodes.add(focusNode);
        for (int i = a; i < formule.length(); i++) {
            String chars = formule.substring(i, i + 1);
            if (chars.equals("-") || chars.equals("M") || chars.equals("L")) {
                chars = formule.substring(i, i + 2);
                i++;
            }

            if (focusNode.getLeftChild() == null) {
                if (chars.substring(0, 1).equals("&") || chars.substring(0, 1).equals("|") || chars.substring(0, 1).equals(">")) {

                    focusNode = focusNode.addNode(chars, true);
                    focusNodes.add(focusNode);

                } else {

                    focusNode.addNode(chars, true);
                }
            } else {
                if (chars.substring(0, 1).equals("&") || chars.substring(0, 1).equals("|") || chars.substring(0, 1).equals(">")) {

                    focusNode = focusNode.addNode(chars, false);
                    if (focusNodes.size() > 0) {
                        focusNodes.remove(focusNodes.size() - 1);
                    }
                    focusNodes.add(focusNode);

                } else {

                    focusNode.addNode(chars, false);
                    if (focusNodes.size() > 0) {
                        focusNodes.remove(focusNodes.size() - 1);
                        if (focusNodes.size() > 0) {
                            focusNode = focusNodes.get(focusNodes.size() - 1);
                        }
                    }
                }
            }
        }
        return nodeRoot;
    }
}
