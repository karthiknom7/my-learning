package com.logical.ds.ll;

import java.util.ListIterator;

public class DoublyLinkedListIterator implements ListIterator<Dlink>{
	
	private Dlink current;
	
	public  DoublyLinkedListIterator(DoublyLinkedList doublyLinkedList) {
		current = doublyLinkedList.first;
	}

	@Override
	public void add(Dlink e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasNext() {
		return(current.next != null);
	}

	@Override
	public boolean hasPrevious() {
		return (current.previous != null);
	}

	@Override
	public Dlink next() {
		Dlink theLink = null;
		if(hasNext()){
			theLink =  current.next;
			current = current.next;
		}
		return theLink;
	}

	@Override
	public int nextIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Dlink previous() {
		Dlink theLink = null;
		if(hasPrevious()) {
			theLink =  current.previous;
			current = current.previous;
		}
		return theLink;
	}

	@Override
	public int previousIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void remove() {
		current.previous.next= current.next;
		current.next.previous = current.previous;
		Dlink removed = current;
		current = current.next;
		removed.next = null;
		removed.previous = null;
	}

	@Override
	public void set(Dlink e) {
		// TODO Auto-generated method stub
		
	}

}
