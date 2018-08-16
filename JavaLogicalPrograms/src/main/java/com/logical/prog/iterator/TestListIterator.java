package com.logical.prog.iterator;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TestListIterator {

	public static void main(String[] args) {

		List<Emplyee> emps = new LinkedList<>();
		emps.add(new Emplyee("KK", 101));
		emps.add(new Emplyee("KK q", 102));
		emps.add(new Emplyee("Kg", 103));
		emps.add(new Emplyee("oK", 104));

		ListIterator<Emplyee> listIterator = emps.listIterator();
		while (listIterator.hasNext()) {
			System.out.println(listIterator.next());
		}
		System.out.println("Done");
		while (listIterator.hasPrevious()) {
			Emplyee emplyee = listIterator.previous();
			System.out.println(emplyee);
			if (emplyee.getId() == 103) {
				listIterator.add(new Emplyee("added", 19));
			}
		}

		System.out.println("Done");
		listIterator = emps.listIterator(emps.size());
		while (listIterator.hasPrevious()) {
			System.out.println(listIterator.previous());
		}
		
	}
	
	static Comparator<Emplyee> myComparator = new Comparator<Emplyee>() {
		
		public int compare(Emplyee o1, Emplyee o2) {
			return (o1.getId() < o2.getId())?-1:(o1.getId() == o2.getId())? 0:1;
		}
	};

}
