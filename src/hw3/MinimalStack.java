package hw3;


import java.util.Stack;

public class MinimalStack {
    public static void main(String[]args){
        MinimalStack minimalStack = new MinimalStack();
        minimalStack.pop();
        minimalStack.push(2);
        minimalStack.push(1);
        minimalStack.push(3);
        minimalStack.push(4);
        System.out.println(minimalStack.findMin().toString());
        minimalStack.pop();
        System.out.println(minimalStack.findMin().toString());
    }

    public Stack<Integer> mainStack;
    public Stack<Integer> minStack;

    public MinimalStack() {
        this.mainStack = new Stack<>();
        this.minStack = new Stack<>();
    }


    public void push(Integer integer) {
        if (this.mainStack.empty()) {
            this.minStack.push(integer);
        } else {
            if (integer < this.minStack.peek()) this.minStack.push(integer);
            else this.minStack.push(this.minStack.peek());
        }
        this.mainStack.push(integer);
    }

    public Integer pop() {
        if (this.mainStack.empty()) {
            System.out.println("Empty stack");
            return null;
        } else {
            this.minStack.pop();
            return this.mainStack.pop();
        }
    }

    public Integer findMin() {
        if (this.mainStack.empty()) {
            System.out.println("Empty stack");
            return null;
        } else {
            return this.minStack.peek();
        }
    }
}
