package com.logical.algorithms;

import java.util.Random;

public class AlgoUtil {
	
	private static Random random = new Random();
	public static int[] fillRandomArray(int size){
		int[] myArr = new int[size];
		for(int i=0; i< size; i++){
			myArr[i] = random.nextInt(size);
		}
		return myArr;
	}
	
	public static void swap(int[] data, int index1, int index2){
		int temp = data[index1];
		data[index1] = data[index2];
		data[index2] = temp;
	}
}
