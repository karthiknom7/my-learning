package com.logical.prog.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Employees implements Iterable<Emplyee>{
	private List<Emplyee> emps;
	
	public List<Emplyee> getEmps() {
		return emps;
	}

	public  Employees() {

		emps = new ArrayList<>();
		emps.add(new Emplyee("KK", 101));
		emps.add(new Emplyee("KK q", 102));
		emps.add(new Emplyee("Kg", 103));
		emps.add(new Emplyee("oK", 104));
		
	}

	@Override
	public Iterator<Emplyee> iterator() {
		return emps.iterator();
	}

	public static void main(String[] args) {
		Employees employees = new Employees();
		for(Emplyee emplyee:employees){
			System.out.println(emplyee);
		}
		
		
	}
}
