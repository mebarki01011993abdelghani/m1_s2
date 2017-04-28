package ia_1;

import java.util.ArrayList;
import java.util.Arrays;

public final class Prefixer {

    private final String[] binaires = new String[]{"|", "&", ">"};
    private final String[] unaires = new String[]{"-", "L", "M"};

    private String infixFormula;
    private String prefixFormula;
    private String negativeFormula;

    private final Noeud racine;
    private final Noeud negRacine;

    public Prefixer(String s) {
        String[] ss = s.split("");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(ss));
        racine = createTree(list);
        negRacine = negation(racine);
        infixFormula = racine.afficheInfix();
        prefixFormula = racine.affichePrefix();
        negativeFormula = negRacine.affichePrefix();
    }

    public String getInfixFormula() {
        return infixFormula;
    }

    public String getPrefixFormula() {
        return prefixFormula;
    }

    public String getNegativeFormula() {
        return negativeFormula;
    }

    public Noeud createTree(ArrayList<String> s) {
        if (s.isEmpty()) {
            return null;
        }

        ArrayList<String> gauche = new ArrayList<>();
        ArrayList<String> droite = new ArrayList<>();
        String valeur = null;

        boolean binaire = false;

        for (int i = 0; i < s.size(); i++) {
            if (s.get(i).equals("(")) {
                i++;
                int nbPar = 1;
                while (nbPar > 0) {
                    if (s.get(i).equals("(")) {
                        nbPar++;
                    }
                    if (s.get(i).equals(")")) {
                        nbPar--;
                    }
                    i++;
                }
            }
            if (i < s.size() && isBinaire(s.get(i))) {
                binaire = true;
                valeur = s.get(i);
                gauche = new ArrayList<>(s.subList(0, i));
                droite = new ArrayList<>(s.subList(i + 1, s.size()));
                break;
            }
        }

        if (!binaire) {
            if (isUnaire(s.get(0))) {
                ArrayList<String> temp = new ArrayList<>(removePar(new ArrayList<>(s.subList(1, s.size()))));
                return new Noeud(createTree(temp), s.get(0), null);
            } else {
                return new Noeud(null, s.get(0), null);
            }
        }
        return new Noeud(createTree(removePar(gauche)), valeur, createTree(removePar(droite)));
    }

    public ArrayList<String> removePar(ArrayList<String> s) {
        if ((s.get(0).equals("(") && s.get(s.size() - 1).equals(")"))) {
            int nbpar = 1;
            for (int i = 1; i < s.size(); i++) {

                if (s.get(i).equals("(")) {
                    nbpar++;
                }
                if (s.get(i).equals(")")) {
                    nbpar--;
                }
                if (nbpar == 0) {
                    s.remove(s.size() - 1);
                    s.remove(s.get(0));
                    break;
                }
            }
        }
        return s;
    }

    public Noeud negation(Noeud racine) {
        Noeud newRacine = new Noeud(racine, "-", null);

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

        public Noeud(Noeud gauche, String value, Noeud droit) {
            this.gauche = gauche;
            this.value = value;
            this.droit = droit;
        }

        public String afficheInfix() {
            String result = "";

            if (isUnaire(value)) {
                result += value;
                if (gauche != null) {
                    if (isBinaire(gauche.getValue())) {
                        result += "(" + gauche.afficheInfix() + ")";
                    } else {
                        result += gauche.afficheInfix();
                    }
                }

                if (droit != null) {
                    if (isBinaire(droit.getValue())) {
                        result += "(" + droit.afficheInfix() + ")";
                    } else {
                        result += droit.afficheInfix();
                    }
                }

            } else {
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

        public void setValue(String value) {
            this.value = value;
        }

        public Noeud getGauche() {
            return gauche;
        }

        public void setGauche(Noeud gauche) {
            this.gauche = gauche;
        }

        public Noeud getDroit() {
            return droit;
        }

        public void setDroit(Noeud droit) {
            this.droit = droit;
        }
        
    }
}