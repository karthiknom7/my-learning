package com.harman.kconsumer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.logical.junit.Calculat;

public class CalculateTest {

	@BeforeClass
	public static void setBeforClass(){
		System.out.println("Befor class will be excuted only once");
	}
	
	@Before
	public void setBeforTest(){
		System.out.println("Before wil be call before each test execution");
	}
	
	@Test
	public void test1(){
		System.out.println("test1 excuted");
		Assert.assertEquals(4, Calculat.findMax(new int[]{1,3,4,2}));
	}
	@Test
	public void test2(){
		System.out.println("tese 2 excuted");
		Assert.assertNotEquals(4, new int[]{1,3,4,2});
	}
	
	@Test
	public void test3(){
		System.out.println("Test 3 executed...");
		Assert.assertEquals("kart", Calculat.reverseWord("trak"));
	}
	
	@After
	public void setAfter(){
		System.out.println("Set after will be call after each test excution");
	}
	
	@AfterClass
	public static void setAfterClass(){
		System.out.println("Set after call will be called once all tests excuted");
	}
}
