package com.logical.prog.logic;

public class FirstNonRepeatedChar {

	public static void main(String[] args) {
		String input = "1192233445";
		findIt(input);
		System.out.println(input.contains(input.substring(0,1)));
	}
	
	public static void findIt(String input){
		for(int i =0; i < input.length(); i++){
			String currentStr= input.substring(i, i+1);
			System.out.println(currentStr);
			String otherPart = input.substring(0, i) + input.substring(i+1,input.length());
			if(!otherPart.contains(currentStr)){
				System.out.println(" First non repeated is :" + currentStr);
				return;
			}
		}
		System.out.println("No non-repeated char");
	}

}
