package ia_1;

public class BinaryTree {

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
        formula = buildTree(formula, nodeRoot);
        String[] next = Formula.getNextNodeName(formula);
        String nameNode = next[0];
        formula = next[1];
        nodeRoot.setRightChild(new Node(nameNode));
        buildTree(formula, nodeRoot.getRightChild());
        return nodeRoot;
    }

    public static String buildTree(String formule, Node focusNode) {
        String[] next;
        String nameNode;
        if (focusNode.getName().equals("")) {
            next = Formula.getNextNodeName(formule);
            nameNode = next[0];
            formule = next[1];
            focusNode.setName(nameNode);
            next = Formula.getNextNodeName(formule);
            nameNode = next[0];
            formule = next[1];
            focusNode.setLeftChild(new Node(nameNode));
        }
        if (focusNode.getLeftChild() != null) {
            if (focusNode.getLeftChild().getName().endsWith("&")
                    || focusNode.getLeftChild().getName().endsWith("|")
                    || focusNode.getLeftChild().getName().endsWith(">")) {
                focusNode = focusNode.getLeftChild();
                next = Formula.getNextNodeName(formule);
                nameNode = next[0];
                formule = next[1];
                focusNode.setLeftChild(new Node(nameNode));
                formule = buildTree(formule, focusNode);

                if (focusNode.getLeftChild() != null && focusNode.getRightChild() == null) {
                    next = Formula.getNextNodeName(formule);
                    nameNode = next[0];
                    formule = next[1];
                    focusNode.setRightChild(new Node(nameNode));//B
                    if (nameNode.endsWith("&")
                            || nameNode.endsWith("|")
                            || nameNode.endsWith(">")) {
                        formule = buildTree(formule, focusNode.getRightChild());
                    }
                }
            }
        } else {
            next = Formula.getNextNodeName(formule);
            nameNode = next[0];
            formule = next[1];
            if (focusNode.getLeftChild() == null && focusNode.getRightChild() == null) {
                focusNode.setLeftChild(new Node(nameNode));
                if (nameNode.endsWith("&")
                        || nameNode.endsWith("|")
                        || nameNode.endsWith(">")) {
                    formule = buildTree(formule, focusNode.getLeftChild());
                }
                next = Formula.getNextNodeName(formule);
                nameNode = next[0];
                formule = next[1];
                focusNode.setRightChild(new Node(nameNode));
                if (nameNode.endsWith("&")
                        || nameNode.endsWith("|")
                        || nameNode.endsWith(">")) {
                    formule = buildTree(formule, focusNode.getRightChild());
                }
            } else if (focusNode.getLeftChild() != null) {
                focusNode.setRightChild(focusNode);
                if (nameNode.endsWith("&")
                        || nameNode.endsWith("|")
                        || nameNode.endsWith(">")) {
                    formule = buildTree(formule, focusNode.getRightChild());
                }
            } else {
                focusNode.setLeftChild(new Node(nameNode));
                if (nameNode.endsWith("&")
                        || nameNode.endsWith("|")
                        || nameNode.endsWith(">")) {
                    formule = buildTree(formule, focusNode.getLeftChild());
                }
            }
        }

        return formule;

    }

}
