package com.logical.prog.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class FindANumber {

	
	static String findNumber(int[] arr, int k) {

		if (Stream.of(arr).anyMatch(ele -> ele.equals(k))) {
			return "YES";
		} else {
			return "NO";
		}

	}

	static int[] oddNumbers(int l, int r) {

		List<Integer> list = new ArrayList<Integer>();
		for (int i = l; i <= r; i++) {
			if (i % 2 != 0) {
				list.add(i);
			}
		}

		int arr[] = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arr[i] = list.get(i);
		}

		return arr;
	}
	
	
	static String findQualifiedNumbers(int[] numberArray) {

		List<Integer> results = new ArrayList<>();
		for(Integer ele : numberArray){
			String strInt = ele.toString();
			if(strInt.contains("1") && strInt.contains("2") && strInt.contains("3")){
				results.add(ele);
			}
		}
		
		if(results.isEmpty()){
			return "-1";
		}else{
			Collections.sort(results);
			String strResult = "";
			for(int i = 0 ; i <  results.size() -1 ; i++){
				strResult +=results.get(i) + ",";
			}
			strResult += results.get(results.size() -1);
			return strResult;
		}
		
		
		
		
		
    }
	
	
	
	
	
	static int findPossibleSmallestNumberMatchingPattern(String pattern) {

		if(pattern == null || pattern.equals("")){
			return -1;
		}
	List<Integer> results = new ArrayList<>();
	int currentDigit = pattern.length() +1 ;
	if(pattern.length() <=1){
		 currentDigit = pattern.length() + 1;
	}else{
		 currentDigit = pattern.length() ;
	}
	results.add(currentDigit);
		for(char eachChar :pattern.toCharArray() ){
			if(eachChar == 'M'){
				int decDigit = getDecrmentedDigit(results, currentDigit);
				currentDigit = decDigit;
				results.add(currentDigit);
			}else if(eachChar == 'N'){
				int incDigit = getIncrmentedDigit(results, currentDigit);
				currentDigit = incDigit;
				results.add(currentDigit);
				
			}else{
				return -1;
			}
		}
		
		String strResult = "";
		if(results.isEmpty()){
			return -1;
		}else{
			for(int ele :results){
				strResult += ele;
			}
			
			return Integer.parseInt(strResult);
		}
    }
	
	private static int getDecrmentedDigit(List<Integer> results, int currentDigit ){
		int decDidgt = currentDigit;
		while (containsDigit(results, --decDidgt));
		return decDidgt;
	}
	
	private static int getIncrmentedDigit(List<Integer> results, int currentDigit ){
		int incDidgt = currentDigit;
		while (containsDigit(results, ++incDidgt));
		return incDidgt;
	}
	
	private static boolean containsDigit(List<Integer> results , int element){
		Set<Integer> digitSet = new HashSet<>(results);
		if(digitSet.contains(element)){
			return true;
		}else{
			return false;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(findPossibleSmallestNumberMatchingPattern("NMNNM"));
	}
}
