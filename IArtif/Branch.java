package ia_1;

import java.util.ArrayList;

public class Branch {

    ArrayList<NodeCB> branch;
    ArrayList<NodeCB> litteraux;

    boolean propage;
    int indice;

    public Branch() {
        this.branch = new ArrayList<NodeCB>();
        this.propage = false;
        this.litteraux = new ArrayList<NodeCB>();
    }

    public ArrayList<NodeCB> getBranch() {
        return branch;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public void setBranch(ArrayList<NodeCB> branch) {
        this.branch = branch;
    }

    public ArrayList<NodeCB> getLitteraux() {
        return litteraux;
    }

    public void setLitteraux(ArrayList<NodeCB> litteraux) {
        this.litteraux = litteraux;
    }

    public boolean isPropage() {
        return propage;
    }

    public void setPropage(boolean propage) {
        this.propage = propage;
    }

    public static void factoryBranch(NodeCB node) {
        node.setLeft(new Branch());
        node.setRight(new Branch());
    }

}
