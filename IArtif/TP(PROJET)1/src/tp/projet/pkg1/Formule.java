package tp.projet.pkg1;

import java.util.ArrayList;

public class Formule {

    private ArrayList<Character> formule;

    public Formule(String formule) {
        this.formule = factoryFormule(formule);
    }

    public ArrayList<Character> getFormule() {
        return formule;
    }

    public void setFormule(ArrayList<Character> formule) {
        this.formule = formule;
    }

    // Construction de la formule prop

    private ArrayList<Character> factoryFormule(String formule) {
        ArrayList<Character> arrayFormule = new ArrayList<Character>();
        //On supprime les blancs
        formule = formule.replace(" ", "");
        String[] formuleTab = formule.split("");
        for (String el : formuleTab) {
            arrayFormule.add(el.charAt(0));
        }
        return arrayFormule;
    }

}
