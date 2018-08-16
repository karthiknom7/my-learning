package com.logical.prog;

import java.util.Stack;

public class StackExample {

	public static void main(String[] args) {

		Stack<Integer> stack = new Stack<>();
		stack.push(100);
		stack.push(190);
		System.out.println(stack);
		stack.push(300);
		System.out.println("Pop:" + stack.pop());
		System.out.println("Pop:" + stack.pop());
		System.out.println(stack);
		System.out.println("Pop:" + stack.pop());
		System.out.println(stack);
		
	}

}
