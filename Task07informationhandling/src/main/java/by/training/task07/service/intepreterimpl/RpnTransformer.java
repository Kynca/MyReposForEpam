package by.training.task07.service.intepreterimpl;

import java.util.Stack;

/**
 * intermediate class which transformed expression to rpn and then calculate it
 */
public class RpnTransformer {

    /**
     * create class context on which transfers transformed to rpn expression and calculate it
     * @param expression string which is an expression
     * @return calculated expression
     */
    public int calculateExp(String expression) {
        Context context = new Context(toRpn(expression));
        return context.calculate();
    }

    private String toRpn(String expression){
        Stack<Character> stack = new Stack();
        String rpn = "";
        for (int i = 0; i < expression.length(); i++) {
            int priority = getPriority(expression.charAt(i));

            if (priority == 0) {
                if (rpn.length() - 1 > 0 && getPriority(rpn.charAt(rpn.length() - 1)) != 0) {
                    rpn += " ";
                }
                rpn += expression.charAt(i);
            } else if (priority == 7 || priority == 6) {
                rpn += " ";
                stack.push(expression.charAt(i));
            } else if (priority == -1) {
                rpn += " ";
                rpn += findCloseBracket(stack);
            } else if (priority > 0) {
                rpn += " ";
                while (true) {
                    if (stack.isEmpty()) {
                        stack.push(expression.charAt(i));
                        break;
                    } else {
                        char temp = stack.peek();
                        if (getPriority(temp) > priority && getPriority(temp) != 7) {
                            if (getPriority(temp) == 4 || getPriority((temp)) == 5) {
                                if (getPriority(rpn.charAt(rpn.length() - 1)) != getPriority(temp)) {
                                    rpn += " " + temp;
                                } else {
                                    rpn += temp;
                                }
                            } else {
                                rpn += " " + temp;
                            }
                            stack.pop();
                        } else {
                            stack.push(expression.charAt(i));
                            break;
                        }
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            char symbol = stack.pop();
            int priority = getPriority(symbol);
            if (priority == 4 || priority == 5) {
                if (rpn.length() - 1 > 0 && getPriority(rpn.charAt(rpn.length() - 1)) == priority) {
                    rpn += symbol;
                } else {
                    rpn += " " + symbol;
                }
            } else {
                rpn += " " + symbol;
            }
        }
        rpn = rpn.replaceAll(" {2,}", " ");
        if(rpn.charAt(0) == ' '){
            rpn = rpn.substring(1);
        }
        return rpn;
    }

    private String findCloseBracket(Stack<Character> stack) {
        String temp = "";
        while (true) {
            char stackChar = stack.pop();
            if (getPriority(stackChar) == 4 || getPriority(stackChar) == 5) {
                if (temp.length() - 1 > 0 && (getPriority(temp.charAt(temp.length() - 1)) == 4 || getPriority(temp.charAt(temp.length() - 1)) == 5)) {
                    temp += stackChar;
                } else {
                    temp += " " + stackChar;
                }
            } else if (getPriority(stackChar) != 7) {
                temp += " " + stackChar;
            } else {
                return temp;
            }
        }
    }

    private int getPriority(char operand) {
        switch (operand) {
            case '~':
                return 6;
            case '|':
                return 1;
            case '^':
                return 2;
            case '&':
                return 3;
            case '<':
                return 4;
            case '>':
                return 5;
            case '(':
                return 7;
            case ')':
                return -1;
            default:
                return 0;
        }
    }
}
