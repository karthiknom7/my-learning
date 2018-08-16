package com.logical.prog;

import java.util.ArrayList;
import java.util.List;

public class GenericsBoundTest {

	public static void main(String[] args) {

		List<Integer> ints = new ArrayList<>();
		ints.add(2);
		ints.add(3);
		ints.add(4);
		ints.add(9);
		System.out.println(sum(ints));
	}
	
	public static double sum(List<? extends Number> numbers){
		
		double sum = 0;
		for(Number num: numbers){
			sum+= num.doubleValue();
		}
		return sum;
		
	}

}
