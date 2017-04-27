/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.util.ArrayList;

public class ClosingBranch {

    static ArrayList<Branch> rooting = new ArrayList<Branch>();

    public Branch getRightBranch(Branch branch) {
        branch.propage = true;
        rooting.remove(rooting.size() - 1);
        return rooting.get(rooting.size() - 1);
    }

    public void displayBranchClose(NodeAlgorithm node) {
        System.out.println("World => " + node.getWorld());
        System.out.println("Litteral => " + node.getLitteral());
    }

    //We check only the branches
    public static boolean checkBranchClose(ArrayList<ArrayList<NodeAlgorithm>> rooting, String name) {
        for (ArrayList<NodeAlgorithm> branch : rooting) {
            for (NodeAlgorithm letteral : branch) {
                if (letteral.getLitteral().equals(Formula.simplifyNegative("-" + name))) {
                    return true;
                }
            }
        }
        return false;
    }

    
}
