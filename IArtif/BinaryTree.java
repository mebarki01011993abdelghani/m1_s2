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

    /* On parcours tous les noeuds, en fonction on applique une regle
Idée du case ?
Recursif ?
Faut-il commencer par le haut ?
     */
    public static void treeExplorer(Node focusNode) {
        if (focusNode != null && focusNode.isSymbol()) {
            switch (focusNode.getName()) {
                case "&":
                    System.out.println("");
                    Rules.AND(focusNode);
                    break;
                case "-&":
                    System.out.println("");
                    Rules.NAND(focusNode);
                    break;
                case "|":
                    System.out.println("");
                    Rules.OR(focusNode);
                    break;
                case "-|":
                    System.out.println("");
                    Rules.NOR(focusNode);
                    break;
                case ">":
                    System.out.println("");
                    Rules.IMPLY(focusNode);
                    break;
                case "->":
                    System.out.println("");
                    Rules.Nimply(focusNode);
                    break;
                case "M":
                    System.out.println("");
                    Rules.Maybe(focusNode);
                    break;
                case "-M":
                    System.out.println("");
                    Rules.Nmaybe(focusNode);
                    break;
                case "L":
                    System.out.println("");
                    Rules.Losange(focusNode);
                    break;
                case "-L":
                    System.out.println("");
                    Rules.Nlosange(focusNode);
                    break;

                default:
                    System.out.println("No rules");
            }
            treeExplorer(focusNode.leftChild);
            treeExplorer(focusNode.rightChild);
        }
    }

    public static Node buildTree(String formule) {

        ArrayList<Node> focusNodes = new ArrayList<Node>();

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
