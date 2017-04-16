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

    public static Node buildTree(String formule) {

        ArrayList<Node> focusNodes = new ArrayList<Node>();

        String root = formule.substring(0, 2);
        Node nodeRoot = new Node(root);
        Node focusNode = nodeRoot;
        focusNodes.add(focusNode);
        Boolean find = false;
        for (int i = 2; i < formule.length(); i++) {
            String chars = formule.substring(i, i + 1);
            if (chars.equals("-") || chars.equals("M") || chars.equals("L")) {
                chars = formule.substring(i + 1, i + 2);
                find = true;
            }

            if (focusNode.getLeftChild() == null) {
                if (chars.equals("&") || chars.equals("|") || chars.equals(">")) {
                    if (find) {
                        chars = formule.substring(i, i + 2);
                        i++;
                    }
                    focusNode = focusNode.addNode(chars, true);
                    focusNodes.add(focusNode);

                } else {
                    if (find) {
                        chars = formule.substring(i, i + 2);
                        i++;
                    }
                    focusNode.addNode(chars, true);
                }
            } else {
                if (chars.equals("&") || chars.equals("|") || chars.equals(">")) {
                    if (find) {
                        chars = formule.substring(i, i + 2);
                        i++;
                    }
                    focusNode = focusNode.addNode(chars, false);
                    if (focusNodes.size() > 0) {
                        focusNodes.remove(focusNodes.size() - 1);
                    }
                    focusNodes.add(focusNode);

                } else {
                    if (find) {
                        chars = formule.substring(i, i + 2);
                        i++;
                    }
                    focusNode.addNode(chars, false);
                    if (focusNodes.size() > 0) {
                        focusNodes.remove(focusNodes.size() - 1);
                        if (focusNodes.size() > 0) {
                            focusNode = focusNodes.get(focusNodes.size() - 1);
                        }
                    }
                }
            }
            find = false;
        }
        return nodeRoot;
    }
}
