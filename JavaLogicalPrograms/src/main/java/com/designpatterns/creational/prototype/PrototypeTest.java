package com.designpatterns.creational.prototype;

import java.util.List;

/**
 * Prototype Pattern says that cloning of an existing object instead of creating
 * new one and can also be customized as per the requirement.
 * 
 * This pattern should be followed, if the cost of creating a new object is
 * expensive and resource intensive.
 * 
 * 
 * @author KNarayanaswa
 *
 */
public class PrototypeTest {

	public static void main(String[] args) throws CloneNotSupportedException {
		Employees emps = new Employees();
		emps.loadData();

		// Use the clone method to get the Employee object
		Employees empsNew = (Employees) emps.clone();
		Employees empsNew1 = (Employees) emps.clone();
		List<String> list = empsNew.getEmpList();
		list.add("John");
		List<String> list1 = empsNew1.getEmpList();
		list1.remove("Pankaj");

		System.out.println("emps List: " + emps.getEmpList());
		System.out.println("empsNew List: " + list);
		System.out.println("empsNew1 List: " + list1);
	}
}
