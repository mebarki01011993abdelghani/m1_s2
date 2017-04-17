/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_a;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Algorithm {
  
static ArrayList<ArrayList<NodeAlgorithm>> rooting = new ArrayList<ArrayList<NodeAlgorithm>>();

    public void displayBranchClose(NodeAlgorithm node){
        System.out.println("World => " + node.getWorld());
        System.out.println("Litteral => " + node.getLitteral());
    }
//We check only the branches
    public static boolean checkBranchClose(ArrayList<ArrayList<NodeAlgorithm>> rooting, String name) {
        for (ArrayList<NodeAlgorithm> branch : rooting) {
            for (NodeAlgorithm letteral : branch) {
                if (letteral.getLitteral().equals(PostFixConverter.simplifyNegative("-" + name))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void findCloseBranches(String formula){
        //Tree
        ArrayList<NodeAlgorithm> root = new ArrayList<NodeAlgorithm>();
        root.add(new NodeAlgorithm(formula));
        
        rooting.add(root);
        
        
    }
}
