package ia_1;

import java.util.ArrayList;
import java.util.Arrays;

public class InfixToPrefix {

    private String[] binaires = new String[]{"|", "&", ">"};
    private String[] unaires = new String[]{"-", "L", "M"};
    private String[] regles = new String[]{""};

    private Noeud root;
    private Noeud negativ;

    public String infixFormula;
    public String negativFormula;
    public String prefixFormula;

    public InfixToPrefix(String s) {
        String[] ss = s.split("");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(ss));
        root = createTree(list);
        negativ = negation(root);
        infixFormula = root.afficheInfix();
        prefixFormula = root.affichePrefix();
        negativFormula = negativ.affichePrefix();

    }

    public void setRoot(Noeud root) {
        this.root = root;
    }

    public Noeud getNegativ() {
        return negativ;
    }

    public void setNegativ(Noeud negativ) {
        this.negativ = negativ;
    }

    public Noeud createTree(ArrayList<String> s) {
        ArrayList<String> gauche = new ArrayList<>();
        ArrayList<String> droite = new ArrayList<>();
        String valeur = null;

        for (int i = 0; i < s.size(); i++) {
            if (isUnaire(s.get(i))) {
                valeur = s.get(i);
                droite = new ArrayList<>(s.subList(i + 1, s.size()));
                break;
            } else if (isBinaire(s.get(i))) {
                valeur = s.get(i);
                droite = new ArrayList<>(s.subList(i + 1, s.size()));
                break;
            } else if (s.get(i).equals("(")) {
                ArrayList<String> temp = new ArrayList<>();
                i++;
                while (!s.get(i).equals(")") && i < s.size()) {
                    gauche.add(s.get(i));
                    temp.add(s.get(i));
                    i++;
                }
            } else {
                gauche.add(s.get(i));
            }
        }
        Noeud left = null;
        Noeud right = null;
        if (gauche.size() > 1) {
            left = createTree(gauche);
        } else if (gauche.size() > 0) {
            left = new Noeud(null, gauche.get(0), null, "X");
        }
        if (droite.size() > 1) {
            right = createTree(droite);
        } else if (droite.size() > 0) {
            right = new Noeud(null, droite.get(0), null, "X");
        }
        Noeud n = null;
        n = new Noeud(left, valeur, right, "X");
        if (valeur == null) {
            return createTree(gauche);
        }
        return n;
    }

    public Noeud negation(Noeud racine) {
        Noeud newRacine = new Noeud(racine, "-", null, "X");
        return newRacine;
    }

    public boolean isUnaire(String s) {
        for (String unaire : unaires) {
            if (unaire.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public boolean isBinaire(String s) {
        for (String binaire : binaires) {
            if (binaire.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public class Noeud {

        private String value;
        private Noeud gauche;
        private Noeud droit;
        private String monde;

        public Noeud(Noeud gauche, String value, Noeud droit, String monde) {
            this.gauche = gauche;
            this.value = value;
            this.droit = droit;
            this.monde = monde;
        }

        public String afficheInfix() {
            String result = "";
            if (gauche != null) {
                if (isBinaire(gauche.getValue())) {
                    result += "(" + gauche.afficheInfix() + ")";
                } else {
                    result += gauche.afficheInfix();
                }
            }
            result += value;
            if (droit != null) {
                if (isBinaire(droit.getValue())) {
                    result += "(" + droit.afficheInfix() + ")";
                } else {
                    result += droit.afficheInfix();
                }
            }
            return result;
        }

        public String affichePrefix() {
            String result = "";
            result += value;
            if (gauche != null) {
                result += gauche.affichePrefix();
            }
            if (droit != null) {
                result += droit.affichePrefix();
            }
            return result;
        }

        public String getValue() {
            return value;
        }
    }
}
