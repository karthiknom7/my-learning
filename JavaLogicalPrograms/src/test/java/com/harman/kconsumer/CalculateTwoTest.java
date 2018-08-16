package com.harman.kconsumer;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.logical.junit.Calculat;

public class CalculateTwoTest {

	@BeforeClass
	public static void beforeClass(){
		System.out.println("before class in CalculateTwoTest");
	}
	
	@Test
	public void findMaxTest(){
		Assert.assertEquals(10, Calculat.findMax(new int[] {4,6,7,8}));
	}
	
	@Test
	public void reversStringNullTest(){
		Assert.assertNull(Calculat.reverseWord(null));
	}
	
	@AfterClass
	public static void afertCalculateTwoTestClass(){
		System.out.println("After CalculateTwoTest run");
	}
}
