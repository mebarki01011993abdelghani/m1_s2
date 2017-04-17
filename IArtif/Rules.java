package ia_1;

public class Rules {

    Node left;
    Node right;
    boolean newBranch;
    boolean newWorld;

    public Rules(){
        
    }
    public Rules(Node left, Node right, boolean newBranch, boolean newWorld) {
        this.left = left;
        this.right = right;
        this.newBranch = newBranch; // True => new branch False => keep branch
        this.newWorld = newWorld;
    }

    /* Prendre en argument 1 node,
    changer ses fils gauches et droits en fonctions des règles
    
    Probleme : comment faire les monde ?
    Idée : arraylist ?
    
    
     */
    
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

}
