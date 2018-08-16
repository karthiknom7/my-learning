package com.logical.ds;

public class ReverseSinglyLinkedList {
	
	 Node head;
	static class Node{
		int data;
		Node next;
		Node(int data){
			this.data = data;
		}
	}
	
	public Node reverseLinkedList(Node head){
		Node next = null;
		Node current = head;
		Node pre = null;
		while(current != null){
			next = current.next;
			current.next = pre;
			pre = current;
			current = next;
		}
		head = pre;
		return head;
	}
	
	public void printList(Node node){
		while(node != null){
			System.out.print(node.data + " ");
			node = node.next;
		}
	}

	public static void main(String[] args) {

		ReverseSinglyLinkedList linkedList = new ReverseSinglyLinkedList();
		linkedList.head = new Node(5);
		linkedList.head.next = new Node(4);
		linkedList.head.next.next = new Node(3);
		linkedList.head.next.next.next = new Node(2);
		linkedList.head.next.next.next.next = new Node(1);
		
		System.out.println("Before " );
		linkedList.printList( linkedList.head);
		linkedList.head = linkedList.reverseLinkedList(linkedList.head);
		System.out.println("After");
		linkedList.printList( linkedList.head);
	}

}
