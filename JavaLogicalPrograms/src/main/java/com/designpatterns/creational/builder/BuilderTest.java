package com.designpatterns.creational.builder;

import com.designpatterns.creational.builder.MyComputer.MyComputerBuilder;

/**
 * This pattern is used when object creation algorithms should be decoupled from
 * the system, and multiple representations of creation algorithms are required.
 * This decoupling is useful as you can add new creation functionality to your
 * system without affecting the core code. You also get control over the
 * creation process at runtime with this approach
 * 
 * @author KNarayanaswa
 *
 */
public class BuilderTest {

	public static void main(String[] args) {

		MyComputer computer = new MyComputerBuilder("4 GB", "500 GB").setisGraphicCardEnabled(true).build();
		System.out.println(computer);
		
	}

}
