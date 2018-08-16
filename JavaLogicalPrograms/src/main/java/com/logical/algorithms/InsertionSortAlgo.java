package com.logical.algorithms;

import java.util.Arrays;

/**
 * Take an elements and insert in array where it sorted 
 * 
 * @author KNarayanaswa
 *
 */
public class InsertionSortAlgo {

	private int[] data;
	
	public InsertionSortAlgo(int arraySize) {
		data = AlgoUtil.fillRandomArray(arraySize);
	}
	
	public void sort(){
		
		System.out.println("Before : " + Arrays.toString(data));
		for(int i=0 ; i < data.length; i++){
			int currentElement = data[i];
			int j = i -1;
			while(j >=0 && data[j] > currentElement){
				data[j+1] = data[j];
				j--;
			}
			
			data[j+1] = currentElement;
		}
		
		
		System.out.println("Insertion sort After : " + Arrays.toString(data));
	}
	
	public static void main(String[] args) {
		
		InsertionSortAlgo insertionSortAlgo = new InsertionSortAlgo(10);
		insertionSortAlgo.sort();
	}
}
