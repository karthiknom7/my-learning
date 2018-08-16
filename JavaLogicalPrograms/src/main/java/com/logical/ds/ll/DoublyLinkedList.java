package com.logical.ds.ll;

public class DoublyLinkedList {

	 Dlink first;
	 Dlink last;

	public boolean isEmpty() {
		return (first == null);
	}

	public void insertInFirstPosition(String value) {
		Dlink theLink = new Dlink(value);

		if (isEmpty()) {
			first = theLink;
			last = theLink;
		} else {
			theLink.next = first;
			first.previous = theLink;
			first = theLink;
		}
	}

	public void insertInLastPosition(String value) {
		Dlink theLink = new Dlink(value);

		if (isEmpty()) {
			last = theLink;
			first = theLink;
		} else {
			theLink.previous = last;
			last.next = theLink;
			last = theLink;
		}
	}

	public void insertAfterValue(String value, String newValue) {
		Dlink theLink = new Dlink(newValue);
		if (!isEmpty()) {
			Dlink currentLink = first;
			boolean found = false;
			while (currentLink != null && found == false) {
				if (currentLink.value.equals(value)) {
					found = true;
				} else {
					currentLink = currentLink.next;
				}
			}

			if (found) {
				if (currentLink == last) {
					insertInLastPosition(newValue);
				} else {
					theLink.next = currentLink.next;
					currentLink.next.previous = theLink;
					currentLink.next = theLink;
					theLink.previous = currentLink;
				}
			} else {
				System.out.println("Value could not find li link : " + value);
			}
		} else {
			insertInFirstPosition(newValue);
		}
	}

	public void insertBeforeValue(String value, String newValue) {
		Dlink theLink = new Dlink(newValue);
		if (!isEmpty()) {
			Dlink currentLink = first;
			boolean found = false;
			while (currentLink != null && found == false) {
				if (currentLink.value.equals(value)) {
					found = true;
				} else {
					currentLink = currentLink.next;
				}
			}

			if (found) {
				if (currentLink == first) {
					insertInFirstPosition(newValue);
				} else {
					theLink.previous = currentLink.previous;
					theLink.next = currentLink;
					currentLink.previous.next = theLink;
					currentLink.previous = theLink;
				}

			} else {
				System.out.println("Value could not find in link : " + value);
			}
		} else {
			insertInFirstPosition(newValue);
		}
	}

	public void display() {
		System.out.print("|null|  ");
		Dlink currentLink = first;
		while (currentLink != null) {
			System.out.print(" <-| " + currentLink.value + " |->  ");
			currentLink = currentLink.next;
		}
		System.out.print(" |null |");
		System.out.println();
	}

	public static void main(String[] args) {

		DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
		doublyLinkedList.insertInFirstPosition("Karthik");
		doublyLinkedList.insertInFirstPosition("jutti");
		doublyLinkedList.insertInLastPosition("myj");
		doublyLinkedList.insertInLastPosition("lv");
		doublyLinkedList.display();
		doublyLinkedList.insertAfterValue("myj", "mu");
		doublyLinkedList.display();
		doublyLinkedList.insertBeforeValue("Karthik", "N");
		// doublyLinkedList.insertBeforeValue("Karthik", "N");
		doublyLinkedList.display();
		
		DoublyLinkedListIterator iterator = new DoublyLinkedListIterator(doublyLinkedList);
		while(iterator.hasNext()){
			System.out.println(iterator.next().value);
		}
	}
}
