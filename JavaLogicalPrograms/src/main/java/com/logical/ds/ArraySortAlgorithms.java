package com.logical.ds;

import java.util.Arrays;

public class ArraySortAlgorithms {

	private int arrayMaxSize = 10;
	private int[] intArray = new int[arrayMaxSize];

	public void generateRandomNums() {
		for (int i = 0; i < arrayMaxSize; i++) {
			intArray[i] = (int) ((Math.random() * 10) + 10);
		}
	}

	public void printArray() {
		System.out.println(Arrays.toString(intArray));
	}

	public void bubbleSort() {
		System.out.println("Started bubble sort....");
		// outer loop from starts from last index
		for (int i = arrayMaxSize - 1; i > 1; i--) {
			// inner loops starts from begging and till i
			for (int j = 0; j < i; j++) {
				if (intArray[j] > intArray[j + 1]) {
					// swap with next greater number
					swap(j, j + 1);
				}
			}
		}
	}

	public void swap(int indexOne, int indexTwo) {
		int temp = intArray[indexOne];
		intArray[indexOne] = intArray[indexTwo];
		intArray[indexTwo] = temp;
	}
	// The Binary Search is quicker than the linear search
	// because all the values are sorted. Because everything
	// is sorted once you get to a number larger than what
	// you are looking for you can stop the search. Also
	// you be able to start searching from the middle
	// which speeds the search. It also works best when
	// there are no duplicates

	public void binarySearchForValue(int value) {
		boolean doesValueExists = false;
		int lowIndex = 0;
		int highIndex = arrayMaxSize - 1;
		while (lowIndex < highIndex) {
			int middleIndex = (lowIndex + highIndex) / 2;
			if (intArray[middleIndex] > value) {
				highIndex = middleIndex - 1;
			} else if (intArray[middleIndex] < value) {
				lowIndex = middleIndex + 1;
			} else {
				System.out.println("Found the match for " + value + " at index " + middleIndex);
				lowIndex = highIndex + 1;
				doesValueExists = true;
			}
		}

		if (!doesValueExists) {
			System.out.println("The value " + value + " is not present ");
		}

	}
	// Selection sort search for the smallest number in the array
	// saves it in the minimum spot and then repeats searching
	// through the entire array each time

	public void selectionSort() {
		System.out.println("Selection sort started....");
		for (int i = 0; i < arrayMaxSize; i++) {
			int min = i;
			// find smallest number and swap to beginning index
			for (int j = i; j < arrayMaxSize; j++) {
				if (intArray[min] > intArray[j]) {
					min = j;
				}
			}
			swap(i, min);
		}
	}

	// The Insertion Sort is normally the best of
	// the elementary sorts. Unlike the other sorts that
	// had a group sorted at any given time, groups are
	// only partially sorted here.
	public void insertionSort() {
		System.out.println("Insertion sort started....");
		for(int i = 1; i < arrayMaxSize ; i ++){
			int j = i;
			int toInsert = intArray[j];
			while( (j > 0) && (intArray[j-1] > toInsert)){
				intArray[j] = intArray[j-1];
				j--;
			}
			intArray[j] = toInsert;
		}
	}

	public static void main(String[] args) {
		ArraySortAlgorithms algorithms = new ArraySortAlgorithms();
		algorithms.generateRandomNums();
		algorithms.printArray();
		// algorithms.bubbleSort();
		//algorithms.selectionSort();
		algorithms.insertionSort();
		algorithms.printArray();
		algorithms.binarySearchForValue(17);
	}
}
