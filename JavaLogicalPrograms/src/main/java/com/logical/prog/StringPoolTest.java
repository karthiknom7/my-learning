package com.logical.prog;

public class StringPoolTest {

	public static void main(String[] args) {
		
		String s1 = "kk";
		String s2 = "kk";
		String s3 = new String("kk");
		System.out.println("s1 == s2 :"+(s1==s2));
        System.out.println("s1 == s3 :"+(s1==s3));
       String s4 = s3.intern();
       System.out.println("s1 == s3 :"+(s1==s3));
        System.out.println("s1 == s4 :"+(s1==s4));
	}

}
