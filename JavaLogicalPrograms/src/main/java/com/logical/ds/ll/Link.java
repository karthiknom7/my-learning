package com.logical.ds.ll;

public class Link {

	private String value;
	public Link next;
	
	public Link(String value) {
		super();
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
	
	public static void main(String[] args) {
		SampleLinkList linkList = new SampleLinkList();
		linkList.insertFirstLink("A");
		linkList.display();
		linkList.insertFirstLink("B");
		linkList.insertFirstLink("C");
		linkList.insertFirstLink("D");
		linkList.insertFirstLink("E");
		linkList.display();
		/*linkList.removeFirst();
		linkList.display();
		linkList.removeFirst();
		linkList.display();*/
		System.out.println("finding  C " + linkList.find("C"));
		linkList.removeLink("A");
		linkList.display();
		linkList.removeLink("C");
		linkList.display();
		linkList.removeLink("E");
		linkList.display();
		
		
	}
}
