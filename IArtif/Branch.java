package ia_1;

import java.util.ArrayList;

public class Branch {

    ArrayList<Node> branch;
    boolean propage;
    int indice; 

    public Branch() {
        this.branch = new ArrayList<Node>();
        this.propage = false;
    }

    public ArrayList<Node> getBranch() {
        return branch;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public void setBranch(ArrayList<Node> branch) {
        this.branch = branch;
    }

    public boolean isPropage() {
        return propage;
    }

    public void setPropage(boolean propage) {
        this.propage = propage;
    }

    public static void factoryBranch(NodeAlgorithm node) {
        node.setLeft(new Branch());
        node.setRight(new Branch());
    }

  
    
}
