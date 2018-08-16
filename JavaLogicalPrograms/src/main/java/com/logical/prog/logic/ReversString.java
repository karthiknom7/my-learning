package com.logical.prog.logic;

public class ReversString {

	public static void main(String[] args) {
		String input = "1234567";
		System.out.println(reverseStr(input));
		System.out.println(reverse(input));
	}
	
	public static String reverseStr(String input){
		if(input.length() <2){
			return input;
		}
		return reverseStr(input.substring(1))+ input.charAt(0);
	}
	
	public static String reverse(String input){
		if(input == null || input.isEmpty()){
			return input;
		}
		String reverse = "";
		for(int i = (input.length()-1); i>=0 ;i--){
			reverse+= input.charAt(i);
		}
		return reverse;
	}
}
