package com.kk.arrays;

import org.junit.Assert;
import org.junit.Test;

import com.logical.prog.logic.arrays.ArrayRotation;

public class ArraysRotationTest {

	@Test
	public void testArrayRotationBy2Elements(){
		int[] intputArr = new int[] {2,4,5,6,4,1};
		int[] expectedArr = new int[] {5,6,4,1,2,4};
		Assert.assertArrayEquals(expectedArr, ArrayRotation.rotateByNElements(intputArr, intputArr.length, 2));
	}
}
