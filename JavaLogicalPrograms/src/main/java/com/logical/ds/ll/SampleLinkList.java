package com.logical.ds.ll;

public class SampleLinkList {

	private Link firstLink;

	public SampleLinkList() {
		firstLink = null;
	}

	public boolean isEmpty() {
		return (firstLink == null);
	}

	public void insertFirstLink(String value) {
		Link newLink = new Link(value);
		newLink.next = firstLink;
		firstLink = newLink;
	}

	public Link removeFirst() {
		Link removedLink = firstLink;
		if (!isEmpty()) {
			firstLink = firstLink.next;
		} else {
			System.out.println("Linked list is empty...");
		}
		removedLink.next = null;
		return removedLink;
	}
	
	public void display(){
		Link theLink = firstLink;
		System.out.print("| ");
		while(theLink != null){
			System.out.print(theLink + " |");
			theLink = theLink.next;
		}
		System.out.println("");
	}
	
	public Link find(String value){
		Link linkRefernce = firstLink;
		Link resultLink = null;
		if(!isEmpty()){
			while(linkRefernce != null){
				if(linkRefernce.toString().equals(value)){
					resultLink = linkRefernce;
					linkRefernce = null;
				}else{
					linkRefernce = linkRefernce.next;
				}
			}
		}
		if(resultLink == null ){
			
			System.out.println(value + " is not found ");
		}
		return resultLink;
	}
	
	public Link removeLink(String value){
		if(value.equals(firstLink.toString())){
			return removeFirst();
		}
		
		Link currentLink = firstLink.next;
		Link previousLink = firstLink;
		boolean found = false;
		while(currentLink != null && found == false){
			if(value.equals(currentLink.toString())){
				found = true;
			}else{
				previousLink = currentLink;
				currentLink = currentLink.next;
			}
		}
		
		if(found){
			previousLink.next = currentLink.next;
			currentLink.next = null;
		}
		
		return currentLink;
	}
}
