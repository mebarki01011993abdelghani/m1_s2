package ia_1;

import java.util.ArrayList;

public class PostFixConverter {

    static int top = -1;
    static ArrayList<Character> stack = new ArrayList<Character>();

    public static Boolean checker(char c) {
        return c == '\t' || c == ' ';
    }

    public static String negative(String str) {
        String prefix = "-(";
        String suffixe = ")";
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

    public static String infix2prefix(String str) throws Exception {
        String postfix = "";
        char symbol;
        char next;
        for (int i = 0; i < str.length(); i++) {
            symbol = str.charAt(i);
            if (!checker(symbol)) {
                switch (symbol) {
                    case '(':
                        push(symbol);
                        break;
                    case ')':
                        next = pop();
                        while (next != '(') {
                            postfix = postfix + next;
                            next = pop();
                        }
                        break;
                    case '&':
                    case '|':
                    case '-':
                    case '>':
                    case 'M':
                    case 'L':
                        while (!isEmpty() && precedence(stack.get(top)) >= precedence(symbol)) {
                            postfix = postfix + pop();
                        }
                        push(symbol);
                        break;
                    default:
                        postfix = postfix + symbol;
                }
            }
        }

        while (!isEmpty()) {
            postfix = postfix + pop();
        }

        return new StringBuilder(postfix).reverse().toString();
    }
}
