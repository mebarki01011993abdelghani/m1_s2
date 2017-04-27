package projet;

import java.util.ArrayList;

public class NodeAlgorithm {

    String litteral;
    Branch left;
    Branch right;
    String world;

    public NodeAlgorithm(String litteral, Branch left, Branch right, String world) {
        this.litteral = litteral;
        this.left = left;
        this.right = right;
        this.world = world;
    }

    public String getLitteral() {
        return litteral;
    }

    public void setLitteral(String litteral) {
        this.litteral = litteral;
    }

    public Branch getLeft() {
        return left;
    }

    public void setLeft(Branch left) {
        this.left = left;
    }

    public Branch getRight() {
        return right;
    }

    public void setRight(Branch right) {
        this.right = right;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

   

}
