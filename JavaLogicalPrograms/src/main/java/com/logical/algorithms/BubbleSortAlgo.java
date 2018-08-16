package com.logical.algorithms;

import java.util.Arrays;

/**
 * Move largest number to right side in each iteration 
 * Time complexity is O(n^2)
 * 
 * @author KNarayanaswa
 *
 */
public class BubbleSortAlgo {

	private int[] data;
	
	
	public BubbleSortAlgo(int arraySize) {
		this.data = AlgoUtil.fillRandomArray(arraySize);
	}
	
	public void sort(){
		System.out.println( "Before Sort " + Arrays.toString( data));
		long start = System.currentTimeMillis();
		for(int i=data.length-1 ; i > 0; i--){
			for(int j =0; j< i ; j++){
				if(data[j] > data[j+1]){
					AlgoUtil.swap(data, j, j+1);
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("After sort : " + Arrays.toString( data));
		System.out.println("Total time taken : " + (end-start));
		
	}
	
	
	
	
	public static void main(String[] args) {
		BubbleSortAlgo bubbleSortAlgo = new BubbleSortAlgo(10);
		bubbleSortAlgo.sort();
	}
	
	
	

}
