package com.logical.prog;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueExample {

	public static void main(String[] args) {

		Queue<String> myQueue = new ArrayBlockingQueue<>(2);
		myQueue.add("1");
		myQueue.add("2");
		System.out.println(myQueue);
		System.out.println("Poll " + myQueue.poll());
		myQueue.add("3");
		System.out.println(myQueue);
		System.out.println("Poll " + myQueue.poll());
		System.out.println("Poll " + myQueue.poll());
		myQueue.offer("4");
		System.out.println(myQueue);
		
		Queue<String> myLinkQueue = new LinkedList<>();
		myLinkQueue.add("1");
		myLinkQueue.add("12");
		myLinkQueue.add("13");
		myLinkQueue.add("14");
		myLinkQueue.add("16");
		System.out.println("Linked queue " + myLinkQueue);
		System.out.println("Poll in link " + myLinkQueue.poll());
		System.out.println("Linked queue " + myLinkQueue);
	}

}
