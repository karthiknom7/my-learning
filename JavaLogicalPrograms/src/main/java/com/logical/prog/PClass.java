package com.logical.prog;

public class PClass extends BClass {

	public int x =20;
	
	public static void main(String[] args) {
		BClass bClass = new PClass();
		System.out.println(bClass.x);
	}
}
