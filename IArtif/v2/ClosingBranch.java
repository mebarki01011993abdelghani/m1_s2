package ia_1;

import java.util.ArrayList;

public class ClosingBranch {

    static ArrayList<Branch> rooting = new ArrayList<Branch>();

    public Branch getRightBranch(Branch branch) {
        branch.setPropage(true);
        rooting.remove(rooting.size() - 1);
        return rooting.get(rooting.size() - 1);
    }

    public void displayBranchClose(NodeCB node) {
        System.out.println("World => " + node.getWorld());
        System.out.println("Litteral => " + node.getLitteral());
    }

    public static void testTree() {
        Branch branche = new Branch();
        branche.getBranch().add(new NodeCB("-|&-pq|p-q"));
        branche.getBranch().add(new NodeCB("-&-pq"));
        NodeCB lastNode = new NodeCB("-|p-q");
        branche.getBranch().add(lastNode);
        Branch brancheGauche = new Branch();
        Branch brancheDroite = new Branch();
        brancheGauche.getLitteraux().add(new NodeCB("-p"));
        brancheGauche.getLitteraux().add(new NodeCB("p"));
        brancheGauche.getLitteraux().add(new NodeCB("q"));
        brancheDroite.getLitteraux().add(new NodeCB("-p"));
        brancheDroite.getLitteraux().add(new NodeCB("-q"));
        brancheDroite.getLitteraux().add(new NodeCB("q"));

        lastNode.setLeft(brancheGauche);
        lastNode.setRight(brancheDroite);
        brancheGauche.getBranch().add(new NodeCB("--p"));
        brancheGauche.getBranch().add(new NodeCB("-p"));
        brancheGauche.getBranch().add(new NodeCB("--q"));
        brancheGauche.getBranch().add(new NodeCB("p"));
        brancheGauche.getBranch().add(new NodeCB("q"));
        brancheGauche.getBranch().add(new NodeCB("A"));
        brancheDroite.getBranch().add(new NodeCB("-q"));
        brancheDroite.getBranch().add(new NodeCB("-p"));
        brancheDroite.getBranch().add(new NodeCB("--q"));
        brancheDroite.getBranch().add(new NodeCB("q"));

        findClosedBranch(branche, new ArrayList<>());
    }

    public static void findContradiction(ArrayList<NodeCB> litteraux) {
        ArrayList<NodeCB> contradictions = new ArrayList<>();
        for (int i = 0; i < litteraux.size(); i++) {
            for (int a = i + 1; a < litteraux.size(); a++) {
                if (litteraux.get(a).getLitteral().equals(Formula.simplifyNegative("-" + litteraux.get(i).getLitteral()))
                        && (litteraux.get(a).getWorld().equals(litteraux.get(i).getWorld()))) {
                    contradictions.add(litteraux.get(i));
                    contradictions.add(litteraux.get(a));
                }
            }
        }
        if (!contradictions.isEmpty()) {
            System.out.print("There is some contradictions : {");
            contradictions.forEach((contradiction) -> {
                System.out.print(contradiction.getLitteral() + "");
            });
            System.out.println("}");
        }
    }

    public static void findClosedBranch(Branch root, ArrayList<NodeCB> tryBranch) {
        tryBranch.addAll(root.getLitteraux());
        for (int i = 0; i < root.getBranch().size(); i++) {
            if (i == root.getBranch().size() - 1) {
                if (root.getBranch().get(i).getLeft() == null) {
                    findContradiction(tryBranch);
                    for (int f = root.litteraux.size(); f > 0; f--) {
                        tryBranch.remove(tryBranch.size() - 1);
                    }
                } else {
                    findClosedBranch(root.getBranch().get(i).getLeft(), tryBranch);
                    findClosedBranch(root.getBranch().get(i).getRight(), tryBranch);
                }
            }
        }
    }

    //We check only the branches
    public static boolean checkBranchClose(Node root, ArrayList<ArrayList<NodeCB>> rooting, String name) {

        return true;
    }

}
