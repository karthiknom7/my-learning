package com.logical.prog.logic;

public class CheckPolindromeStr {

	public static void main(String[] args) {
		String input = "abab";
		System.out.println(isPolindrom(input));
		System.out.println(isPolindromWithBuilder(input));
		System.out.println(isPolondromWithRecirsive(input));
	}
	
	public static boolean isPolindrom(String inputStr){
		
		for(int i=0; i<(inputStr.length()/2);i++){
			if(inputStr.charAt(i) != inputStr.charAt(inputStr.length()-i-1)){
				return false;
			}
		}
		return true;
	}

	public static boolean isPolindromWithBuilder(String inputStr){
		System.out.println("inputStr " + inputStr);
		StringBuffer sb = new StringBuffer(inputStr);
		String revStr= sb.reverse().toString();
		if(inputStr.equals(revStr)){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isPolondromWithRecirsive(String str){
		
		if(str.length() <= 1){
			return true;
		}
		String first = str.substring(0,1);
		String last = str.substring(str.length()-1);
		if(!first.equals(last)){
			return false;
		}else{
			return isPolondromWithRecirsive(str.substring(1, str.length()-1));
		}
	}
	
	
}
