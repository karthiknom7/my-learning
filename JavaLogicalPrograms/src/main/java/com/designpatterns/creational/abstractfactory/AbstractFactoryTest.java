package com.designpatterns.creational.abstractfactory;

public class AbstractFactoryTest {

	public static void main(String[] args) {

		Computer pc = ComputerFactory.getComputer(new PCFactory("4 GB" , "500 GB", "2.9 GHz"));
		System.out.println("Done");
	}

}
