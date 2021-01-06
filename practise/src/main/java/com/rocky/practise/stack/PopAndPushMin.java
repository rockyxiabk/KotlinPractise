package com.rocky.practise.stack;

import java.util.Stack;

/**
 * Q:实现一个包含 min() 函数的栈，该方法返回当前栈中最小的值。
 * <p>
 * A:使用一个额外的 minStack，栈顶元素为当前栈中最小的值。在对栈进行 push 入栈和 pop 出栈操作时，
 * 同样需要对 minStack 进行入栈出栈操作，从而使 minStack 栈顶元素一直为当前栈中最小的值。
 * 在进行 push 操作时，需要比较入栈元素和当前栈中最小值，将值较小的元素 push 到 minStack 中
 */
public class PopAndPushMin {
    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int mode) {
        dataStack.push(mode);
        minStack.push(minStack.isEmpty() ? mode : Math.min(minStack.peek(), mode));
    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
