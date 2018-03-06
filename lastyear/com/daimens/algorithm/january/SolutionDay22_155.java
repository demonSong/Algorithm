package com.daimens.algorithm.january;

import java.util.Stack;

/**
 * 
 * @author Demon Song
 * 155.Min Stack
 * Design a stack that supports push,pop,top,and retrieving the minimum
 * element in constant time.
 * 1. push(x) -- push element x onto stack.
 * 2. pop() -- removes the element on top of the stack.
 * 3. top() -- get the top element.
 * 4. getMin() -- retrieve the minimum element in the stack.
 * Example:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 *
 */
public class SolutionDay22_155 {
	
	

}

class MinStack {
	Stack<Integer> minStack;
	Stack<Integer> stack;
    /** initialize your data structure here. */
    public MinStack() {
    	minStack = new Stack<Integer>();
    	stack = new Stack<Integer>();
    }
    
    public void push(int x) {
    	if(minStack.isEmpty() || x <= minStack.peek())
    		minStack.push(x);
        stack.push(x);
    }
    
    public void pop() {
    	if(stack.peek().equals(minStack.peek()))
    		minStack.pop();
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}