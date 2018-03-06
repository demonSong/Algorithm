package com.daimens.algorithm.december;

import java.util.Stack;

/**
 * 
 * @author Demon Song
 * 232.Implement Queue Using Stacks
 * Implement the following operations of a queue using stacks.
 * push(x) -- Push element x to the back of queue.
 * pop() -- Removes the element from in front of queue.
 * peek() -- Get the front element.
 * empty() -- Return whether the queue is empty.
 * Notes:
 * You must use only standard operations of a stack -- which means only push to top,
 * peek/pop from top, size, and is empty operations are valid.
 * Depending on your language, stack may not be supported natively. 
 * You may simulate a stack by using a list or deque (double-ended queue), 
 * as long as you use only standard operations of a stack.
 * You may assume that all operations are valid 
 * (for example, no pop or peek operations will be called on an empty queue).
 *
 */
public class SolutionDay24_232 {
	
	private Stack<Integer> s1 = new Stack<>();
	private Stack<Integer> s2 = new Stack<>();
	
	private int front;
	// Push element x to the back of queue.
    public void push(int x) {
        if(s1.empty()){
        	front = x;
        }
        while(!s1.empty()){
        	s2.push(s1.pop());
        }
        s2.push(x);
        while(!s2.empty()){
        	s1.push(s2.pop());
        }
    }

    // Removes the element from in front of queue.
    public void pop() {
        s1.pop();
        if(!s1.empty()){
        	front = s1.peek();
        }
    }

    // Get the front element.
    public int peek() {
        return front;
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return s1.isEmpty();
    }
}
