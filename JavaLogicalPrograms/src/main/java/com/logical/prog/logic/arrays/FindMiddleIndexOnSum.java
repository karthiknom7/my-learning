package com.logical.prog.logic.arrays;

public class FindMiddleIndexOnSum {

	public static void main(String[] args) {

		FindMiddleIndexOnSum findMiddleIndexOnSum = new FindMiddleIndexOnSum();
		
		int[] arr1 = new int[]{7,2,3,2,1,3};
		findMiddleIndexOnSum.findMiddleIndex(arr1);
		int[] num = { 2, 4, 4, 5, 4, 1 };
		findMiddleIndexOnSum.findMiddleIndex(num);
	}

	public int findMiddleIndex(int[] numArray) {
		boolean found = false;
		int position = 0;
		while (position < numArray.length) {
			int leftIndex = 0;
			int rightIndex = numArray.length - 1;
			int leftSum = 0;
			int rightSum = 0;
			while (leftIndex < position+1) {
				leftSum += numArray[leftIndex++];
			}
			while (rightIndex > position) {
				rightSum += numArray[rightIndex--];
			}
			System.out.println("Left sum :" + leftSum + " Right sum :" + rightSum);
			if (leftSum == rightSum) {
				found = true;
				break;
			}
			position++;
		}

		if (found) {
			System.out.println("Middle Index is found : " + position);
			return position;
		} else {
			System.out.println("Middle index could not found!");
			return 0;
		}
	}
}
