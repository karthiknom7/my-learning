package com.logical.prog.logic;

public class ReverseNumber {

	public static void main(String[] args) {
		System.out.println( reverseNum(1234));
		System.out.println("Recursive : " + reverseNumRecursely(1234, 0));

	}
	
	public static int reverseNum(int num){
		int sum = 0;
		int reminder = 0;
		while(num > 0){
			reminder = num % 10;
			sum = (sum * 10) + reminder;
			num = num /10;
		}
		return sum;
	}

	public static int reverseNumRecursely(int num, int sum){
		if(num <= 0){
			return sum;
		}
		int r = num % 10;
		sum = (sum*10) + r;
		num = num /10;
		return reverseNumRecursely(num, sum);
	}
}
