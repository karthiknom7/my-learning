package com.logical.serialization;

public class FinalMainTest {

	public static void main(String[] args) {

		Employee employee = new Employee();
		employee.setAddress("Banglore");
		employee.setEmpId(1234);
		employee.setName("KK");
		employee.setSalary(14567);
		finalTest(employee);
	}
	
	public static void finalTest(final Employee employee){
		System.out.println(employee);
		String name = employee.getName();
		System.out.println("Name :" + name);
		
		employee.setName("Karthik");
		System.out.println(employee);
		//employee = new Employee();
	}

}
