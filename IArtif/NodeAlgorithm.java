package ia_a;

import java.util.ArrayList;

public class NodeAlgorithm {


    String litteral;
    ArrayList<ArrayList<NodeAlgorithm>> nodes;
    String world;
    boolean check;

    public NodeAlgorithm(String formula) {
        this.litteral = formula;
        this.nodes = new ArrayList<>();
        this.world = null;
        this.check = false;
    }

    
    

    

    public String getLitteral() {
        return litteral;
    }

    public void setLitteral(String litteral) {
        this.litteral = litteral;
    }

    public ArrayList<ArrayList<NodeAlgorithm>> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<ArrayList<NodeAlgorithm>> nodes) {
        this.nodes = nodes;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

}


