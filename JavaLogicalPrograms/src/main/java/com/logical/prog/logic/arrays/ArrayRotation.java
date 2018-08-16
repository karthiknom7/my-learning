package com.logical.prog.logic.arrays;

import java.util.Arrays;

public class ArrayRotation {

	public static void main(String[] args) {
		int[] intputArr = new int[] {1,2,3,4,5,6,7};
		System.out.println(Arrays.toString(intputArr));
		//intputArr = reverseArray(intputArr);
		//intputArr = rotateByNEleRecursivly(intputArr, 3);
		//System.out.println(Arrays.toString(intputArr));
		int index = binarySearch(intputArr, 30, 0, intputArr.length-1);
		System.out.println("index is " + index);
		
	}

	public static int[] rotateByNElements(int[] arr, int size, int rotateBy){
		for(int i = 0; i< rotateBy ; i++){
			int firstElement = arr[0];
			for(int arrIndex = 0; arrIndex < size -1 ; arrIndex++){
				arr[arrIndex] = arr[arrIndex+1];
			}
			arr[size-1] = firstElement;
		}
	
		return arr;
	}
	
	public static int[] reverseArray(int[] arr){
		int startIndex = 0;
		int endIndex = arr.length - 1;
		int temp = 0;
		while(startIndex < endIndex){
			temp = arr[startIndex];
			arr[startIndex] = arr[endIndex];
			arr[endIndex] = temp;
			startIndex++;
			endIndex--;
		}
		return arr;
	}
	
	public static int[] rotateByNEleRecursivly(int[] arr, int rotateBy){
		if(rotateBy < 1){
			return arr;
		}
		int first = arr[0];
		arr[0] = arr[arr.length -1];
		arr[arr.length -1] = first;
		return rotateByNEleRecursivly(arr, --rotateBy);
		
	}
	
	
	public static int binarySearch(int[] sortedArr, int ele, int low, int high){
		if(high < low){
			return -1;
		}
		
		int mid = (low + high)/2;
		if(ele == sortedArr[mid]){
			return mid;
		}else if(ele > sortedArr[mid]){
			return binarySearch(sortedArr, ele, mid+1, high);
		}else{
			return binarySearch(sortedArr, ele, low, mid-1);
		}
	}
	
}
