package ia_1;

import java.util.Stack;

public class PostFixConverter {

    public static Boolean checker(char c) {
        return c == '\t' || c == ' ';
    }

    public static String negative(String str) {
        String prefix;
        prefix = "-";
        String suffixe = "";
        return prefix + str + suffixe;
    }

    public static int precedence(char c) {
        switch (c) {
            case '(':
                return 0;
            case '&':
            case '|':
            case '>':
                return 2;
            case '-':
            case 'M':
            case 'L':
                return 3;
            default:
                return 0;
        }
    }

    public static String infix2prefix(String str) throws Exception {
        Stack stack = new Stack();

        String postfix = "";
        char symbol;
        Object next;
        for (int i = 0; i < str.length(); i++) {
            symbol = str.charAt(i);
            if (!checker(symbol)) {
                switch (symbol) {
                    case '(':
                        stack.push(symbol);
                        break;
                    case ')':

                        do {
                            next = stack.pop();
                            postfix = postfix + (Character) next;

                        } while (next.equals('('));
                        break;
                    case '&':
                    case '|':
                    case '-':
                    case '>':
                    case 'M':
                    case 'L':
                        while (!stack.isEmpty() && precedence((Character) stack.pop()) >= precedence(symbol)) {
                            postfix = postfix + (Character) stack.pop();
                            System.err.println(postfix);
                        }
                        stack.push(symbol);
                        break;
                    default:
                        postfix = postfix + (Character) symbol;
                }
            }
        }
        while (!stack.isEmpty()) {
            postfix = postfix + (stack.pop().toString());
        }
        return new StringBuilder(postfix).reverse().toString();
    }
}
