package com.logical.serialization;

import java.io.IOException;

public class SerializationTest {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		Employee employee = new Employee();
		employee.setEmpId(1234);
		employee.setName("karthik N");
		employee.setSalary(20000);
		
		
		String fileName = "D:\\Resume\\serializationTest.txt";
		
		//SerializationUtil.serialize(employee, fileName);
		//System.out.println("Done..." + employee);
		
		Employee emp2 = (Employee) SerializationUtil.deserialize(fileName);
		System.out.println("Done....." + emp2);
	}

}
