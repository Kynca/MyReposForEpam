package by.training.task07.service.intepreterimpl;

import by.training.task07.service.Interpreter;

import java.util.Stack;

public class NumberExpression implements Interpreter {
    private int num;

    public NumberExpression(int num){
        this.num = num;
    }

    @Override
    public void interpret(Stack<Integer> stack) {
        stack.push(num);
    }
}
