package com.logical.prog.thread.pc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueuePCtest {

	public static void main(String[] args) {

		BlockingQueue<Integer> sharedQueue = new ArrayBlockingQueue<>(5);
		Thread producer = new Thread(new QueueProducer(sharedQueue));
		Thread consumer = new Thread(new QueueConsumer(sharedQueue));

		producer.start();
		System.out.println("Producer statred ");
		consumer.start();
		System.out.println("Consumer started ");
	}

}
