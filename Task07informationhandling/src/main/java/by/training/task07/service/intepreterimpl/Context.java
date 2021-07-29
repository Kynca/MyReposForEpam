package by.training.task07.service.intepreterimpl;

import by.training.task07.service.Interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Context for Interpreter pattern
 * used for intermediate actions in counting expressions
 */
public class Context {

    private String[] rpn;
    private Stack<Integer> stack = new Stack();
    private List<Interpreter> list = new ArrayList<>();

    public Context(String rpn) {
        if (rpn.charAt(0) == ' ') {
            rpn = rpn.substring(1);
        }
        this.rpn = rpn.split(" ");
    }

    /**
     * calculate expression based on using stack and pattern interpreter
     * @return calculated expression value
     */
    public int calculate() {
        for (int i = 0; i < rpn.length; i++) {
            switch (rpn[i]) {
                case "~":
                    Interpreter interpreter = stack1 -> stack1.push(~stack1.pop());
                    list.add(interpreter);
//                    list.add(new BiComplementExpression(stack));
                    break;
                case ">>":
//                    list.add(new RightShiftExpression(stack));
                    interpreter = stack1 -> {
                        int temp = stack1.pop();
                        stack1.push(stack1.pop() >> temp);
                    };
                    list.add(interpreter);
                    break;
                case ">>>":
                    interpreter = stack1 -> {
                        int temp = stack1.pop();
                        stack1.push(stack1.pop() >>> temp);
                    };
                    list.add(interpreter);
//                    list.add(new UnsignedRightShiftExpression(stack));
                    break;
                case "<<":
                    interpreter = stack1 -> {
                        int temp = stack1.pop();
                        stack1.push(stack1.pop() << temp);
                    };
                    list.add(interpreter);
//                    list.add(new LeftShiftExpression(stack));
                    break;
                case "&":
                    interpreter = stack1 -> {
                        int temp = stack1.pop();
                        stack1.push(stack1.pop() & temp);
                    };
                    list.add(interpreter);
//                    list.add(new AndExpression(stack));
                    break;
                case "^":
                    interpreter = stack1 -> {
                        int temp = stack1.pop();
                        stack1.push(stack1.pop() ^ temp);
                    };
                    list.add(interpreter);
//                    list.add(new ExclusiveOrExpression(stack));
                    break;
                case "|":
                    interpreter = stack1 -> {
                        int temp = stack1.pop();
                        stack1.push(stack1.pop() | temp);
                    };
                    list.add(interpreter);
//                    list.add(new OrExpression(stack));
                    break;
                default:
                    list.add(new NumberExpression(Integer.parseInt(rpn[i])));
            }
        }
        for (Interpreter expression : list) {
            expression.interpret(stack);
        }
        return stack.pop();
    }

}
