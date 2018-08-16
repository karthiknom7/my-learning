package com.logical.prog.thread.pc;

import java.util.LinkedList;
import java.util.Queue;

public class PCTest {

	public static void main(String[] args) {

		Queue<Integer> sharedQueue = new LinkedList<>();
		final int size = 4;
		
		Thread producer = new Thread(new Producer(sharedQueue, size));
		Thread consumer = new Thread(new Consumer(sharedQueue));
		
		producer.start();
		System.out.println("Producer started....");
		consumer.start();
		System.out.println("Consumer started...");
	}

}
