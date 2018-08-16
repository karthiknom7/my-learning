package com.logical.algorithms;

import java.util.Arrays;

public class MergeSortAlgo {

	private int[] data;

	public MergeSortAlgo(int arraySize) {
		data = AlgoUtil.fillRandomArray(arraySize);
	}

	public void sort() {
		System.out.println("Before merge sort : " + Arrays.toString(data));
		mergeSort(data, 0, data.length - 1);
		System.out.println("After merge sort : " + Arrays.toString(data));
	}

	private void mergeSort(int[] array, int start, int end) {
		if (start < end) {
			int mid = (start + end)/2;
			mergeSort(array, start, mid);
			mergeSort(array, mid + 1, end);
			merge(array, start, mid, end);
		}
	}

	private void merge(int[] array, int start, int mid, int end) {

		int leftSize = mid - start + 1;
		int rightSize = end - mid;

		int[] leftPart = new int[leftSize];
		int[] rightPart = new int[rightSize];

		for (int i = 0; i < leftSize; i++) {
			leftPart[i] = array[start+i];
		}

		for (int j = 0; j < rightSize; j++) {
			rightPart[j] = array[mid+1 + j];
		}
		int i = 0, j = 0;
		for (int k = start; k <= end; k++) {
			if ((j >= rightSize) || (i < leftSize && leftPart[i] <= rightPart[j])) {
				array[k] = leftPart[i];
				i++;
			} else {
				array[k] = rightPart[j];
				j++;
			}
		}
	}

	public static void main(String[] args) {

		MergeSortAlgo mergeSortAlgo = new MergeSortAlgo(10);
		mergeSortAlgo.sort();
	}

}
