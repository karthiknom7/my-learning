package com.logical.algorithms;

import java.util.Arrays;

/**
 * Select smallest number and move to left side of array
 * 
 * Time complexity is O(n^2)
 * 
 * @author KNarayanaswa
 *
 */
public class SelectionSortAlgo {

	private int[] data;

	public SelectionSortAlgo(int arraySize) {
		data = AlgoUtil.fillRandomArray(arraySize);
	}

	public void sort() {
		System.out.println("Before : " + Arrays.toString(data));

		for (int i = 0; i < data.length - 1; i++) {
			int smallIndex = i;
			for (int j = i; j < data.length - 1; j++) {
				if (data[smallIndex] > data[j + 1]) {
					smallIndex = j + 1;
				}
			}
			AlgoUtil.swap(data, i, smallIndex);
		}

		System.out.println("After : " + Arrays.toString(data));
	}

	public static void main(String[] args) {

		SelectionSortAlgo algo = new SelectionSortAlgo(10);
		algo.sort();
	}

}
