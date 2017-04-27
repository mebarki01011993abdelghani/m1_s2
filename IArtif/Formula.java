/**
 *
 * Toutes les fonctions qui traitent une formule
 *
 * */
package ia_1;

import java.util.ArrayList;

public class Formula {

    static int top = -1;
    static ArrayList<Character> stack = new ArrayList<Character>();

    public static Boolean checker(char c) {
        return c == '\t' || c == ' ';
    }

    public static String negative(String str) {
        String prefix;
        prefix = "-";
        String suffixe = "";
        return prefix + str + suffixe;
    }

    public static void push(char symbol) {
        top++;
        stack.add(top, symbol);
    }

    public static char pop() throws Exception {
        if (isEmpty()) {
            System.err.println("Stack is Empty");
            throw new Exception();
        }
        return stack.get(top--);
    }

    public static Boolean isEmpty() {
        if (top == -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Fonction permettant de simplifier le nombre de "-" présent dans une
     * formule.
     *
     * @param formula
     * @return
     */
    public static String simplifyNegative(String formula) {
        try {
            for (int i = 0; i < formula.length(); i++) {
                char negative = formula.charAt(i);
                if (negative == '-') {
                    int count = 1;
                    int a = i;
                    negative = formula.charAt(a + 1);
                    while (negative == '-') {
                        count++;
                        negative = formula.charAt(a + count);
                    }

                    int modulo = count % 2;
                    if (modulo == 0) {
                        formula = rewriteString(formula, a, a + count, true);
                    } else {
                        formula = rewriteString(formula, a, a + count, false);
                    }
                }
            }
        } catch (Exception E) {
            System.out.println("Formle simplifier");
        }
        return formula;
    }

    /**
     * Permet de réecrire un String
     *
     * @param formula
     * @param x
     * @param y
     * @param negative
     * @return
     */
    public static String rewriteString(String formula, int x, int y, boolean negative) {
        String newFormula = null;
        newFormula = formula.substring(0, x);
        if (negative == false) {
            newFormula = newFormula + "-";
        }
        newFormula = newFormula + formula.substring(y, formula.length());
        return newFormula;
    }

    /**
     * Fonction permettant de calculer le poid de chaque symbole
     *
     * @param c
     * @return
     */
    public static int precedence(char c) {
        switch (c) {
            case '(':
                return 0;
            case '&':
            case '|':
            case '>':
                return 2;
            case '-':
                return 4;
            case 'M':
            case 'L':
                return 3;
            default:
                return 0;
        }
    }

    /**
     * Factorise une formule
     *
     * @param formula
     * @return
     */
    public static String[] getNextNodeName(String formula) {
        int i = 0;
        int x = 0;
        int y = 0;

        String newFormula = "";
        String[] result = new String[2];
        try {

            char lit = formula.charAt(i);
            if (!isSymbol(lit)) {
                throw new StringIndexOutOfBoundsException();
            }

            Boolean findLitteral = false;
            Boolean findLetter = false;

            while (lit != '&' && lit != '>' && lit != '|') {
                if (findLitteral == true && !isSymbol(lit)) {
                    findLetter = true;
                    break;
                } else {
                    if (lit == 'M' || lit == 'L' || lit == '-') {
                        findLitteral = true;
                    }
                    i++;
                    lit = formula.charAt(i);
                }
            }

            if (findLetter == true) {
                result[0] = formula.substring(0, i + 1);
                result[1] = formula.substring(i + 1, formula.length());
            } else {

                lit = formula.charAt(i + 1);

                if (lit == '-') {
                    lit = formula.charAt(i + 2);
                    if (lit == 'L' || lit == 'M') {
                        if ('-' == formula.charAt(i + 3)) {
                            x = 1;
                        }

                        if (i + 6 + x < formula.length()) {
                            if (formula.charAt(i + 6 + x) == '-') {
                                y = 1;
                            }
                        }

                        if (lit == formula.charAt(i + 5 + x)) {
                            newFormula = formula.substring(0, i);
                            newFormula += "-" + lit;
                            newFormula += formula.charAt(i);
                            newFormula += formula.substring(i + 3, i + 3 + x + 1);
                            newFormula += formula.substring(i + 6 + x, i + 6 + x + y + 1);
                            newFormula += formula.substring(i + 6 + x + y + 1, formula.length());
                            result[1] = newFormula;
                            result = getNextNodeName(result[1]);
                        } else {
                            result[1] = formula.substring(i + 1, formula.length());
                            newFormula = formula.substring(0, i + 1);
                            result[0] = newFormula;
                        }
                    } else {
                        result[1] = formula.substring(i + 1, formula.length());
                        newFormula = formula.substring(0, i + 1);
                        result[0] = newFormula;
                    }
                } else if (lit == 'L' || lit == 'M') {
                    if ('-' == formula.charAt(i + 2)) {
                        x = 1;
                    }
                    if (i + 4 + x < formula.length()) {
                        if (formula.charAt(i + 4 + x) == '-') {
                            y = 1;
                        }
                    }
                    if (lit == formula.charAt(i + 3 + x)) {
                        newFormula = formula.substring(0, i);
                        newFormula += lit; //L
                        newFormula += formula.charAt(i);// L&
                        newFormula += formula.substring(i + 2, i + 2 + x + 1);
                        newFormula += formula.substring(i + 4 + x, i + 4 + x + y + 1);
                        newFormula += formula.substring(i + 4 + x + y + 1, formula.length());
                        result[1] = newFormula;
                        result = getNextNodeName(result[1]);
                    } else {
                        result[1] = formula.substring(i + 1, formula.length());
                        newFormula = formula.substring(0, i + 1);
                        result[0] = newFormula;
                    }
                } else {

                    result[1] = formula.substring(i + 1, formula.length());
                    newFormula = formula.substring(0, i + 1);
                    result[0] = newFormula;
                }

            }
        } catch (StringIndexOutOfBoundsException e) {
            i = 0;
            boolean findLit = false;
            while (!findLit) {
                if (isSymbol(formula.charAt(i))) {
                    i++;
                } else {
                    findLit = true;
                }
            }
            result[0] = formula.substring(0, i + 1);
            result[1] = formula.substring(i + 1, formula.length());
        } finally {
            result[0] = Formula.simplifyNegative(result[0]);
            result[1] = Formula.simplifyNegative(result[1]);

        }

        return result;

    }

    public static Boolean isSymbol(char chars) {
        switch (chars) {
            case '&':
            case '|':
            case 'L':
            case 'M':
            case '-':
                return true;
            default:
                return false;
        }

    }
}