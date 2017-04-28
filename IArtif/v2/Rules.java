package ia_1;

public class Rules {

    Node left;
    Node right;
    private boolean newBranch;
    private boolean newWorld;

    public Rules(){
       
    }
    public Rules(Node left, Node right, boolean newBranch, boolean newWorld) {
        this.left = left;
        this.right = right;
        this.newBranch = newBranch; // True => new branch False => keep branch
        this.newWorld = newWorld;
    }
    public static void main(String[] args) {
        
    }
    public static Rules OR(Node element) {

        Node A = element.getLeftChild();
        Node B = element.getRightChild();

        Rules rule = new Rules(A, B, true, false);

        return rule;
    }

    public static Rules AND(Node element) {

        Node A = element.getLeftChild();
        Node B = element.getRightChild();

        Rules rule = new Rules(A, B, false, false);

        return rule;
    }

    public static Rules NOR(Node element) {
        return AND(element);
    }

    public static Rules NAND(Node element) {
        return OR(element);
    }

    public static Rules IMPLY(Node element) {

        Node A = element.getLeftChild();
        String nameA = A.getName();
        String negativeA = "-" + nameA;
        A.setName(negativeA);
        Node B = element.getRightChild();

        Rules rule = new Rules(A, B, true, false);

        return rule;
    }

    public static Rules Nimply(Node element) {

        Node A = element.getLeftChild();
        Node B = element.getRightChild();
        String nameB = B.getName();
        String negativeB = "-" + nameB;
        A.setName(negativeB);
        Rules rule = new Rules(A, B, false, false);

        return rule;
    }

    public static Rules Ncertitude(Node element) {

        Node A = element.getLeftChild();
        Node B = element.getRightChild();

        Rules rule = new Rules(A, B, true, true);

        return rule;
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
     

}
