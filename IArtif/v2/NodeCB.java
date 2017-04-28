package ia_1;


public class NodeCB {

    private String litteral;
    private Branch left;
    private Branch right;
    private String world;

    public NodeCB(String litteral) {
        this.litteral = litteral;
        this.left = null;
        this.right = null;
        this.world = "W0";
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
