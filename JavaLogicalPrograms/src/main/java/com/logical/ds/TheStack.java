package com.logical.ds;

import java.util.Arrays;

public class TheStack {
	
	private String[] stackArray;
	private int topOfStack = -1;
	private int size;
	
	public TheStack(int size){
		this.size = size;
		stackArray = new String[size];
	}

	public void displayStack(){
		System.out.print("| ");
		for(int i=0; i< size; i++){
			System.out.print(i+", ");
		}
		System.out.print(" |\n");
		System.out.println(Arrays.toString(stackArray));
	}
	
	public void push(String element){
		if(topOfStack + 1 < size){
			topOfStack ++;
			stackArray[topOfStack] = element;
		}else{
			System.out.println("Sorry stack is full...");
		}
	}
	
	public  String pop(){
		String topElement = null;
		if(topOfStack >= 0){
			topElement = stackArray[topOfStack];
			stackArray[topOfStack]= null;
			topOfStack--;
			 System.out.println("POP " + topElement + " Was Removed From the Stack\n");
		}else{
			System.out.println("Sorry Stack is empty");
		}
		return topElement;
	}
	public static void main(String[] args) {
		TheStack stack = new TheStack(3);
		stack.push("10");
		stack.push("09");
		stack.displayStack();
		stack.push("11");
		stack.push("12");
		stack.displayStack();
		stack.pop();
		stack.displayStack();
		stack.pop();
		stack.displayStack();
		stack.pop();
		stack.pop();
		stack.displayStack();
	}
}
