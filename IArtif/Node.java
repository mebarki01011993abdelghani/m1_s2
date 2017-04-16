package ia_1;

class Node {

    String name;
    Boolean direction;

    Node leftChild;
    Node rightChild;

    Node(String name) {
        this.name = name;
        this.leftChild = null;
        this.rightChild = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDirection() {
        return direction;
    }

    public void setDirection(Boolean direction) {
        this.direction = direction;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public Node addNode(String name, Boolean direction) {
        Node newNode = new Node(name);
        if (direction == true) {
            this.leftChild = newNode;
            return this.leftChild;
        } else {
            this.rightChild = newNode;
            return this.rightChild;
        }
    }
}
