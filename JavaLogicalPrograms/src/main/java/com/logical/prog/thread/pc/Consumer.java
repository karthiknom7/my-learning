package com.logical.prog.thread.pc;

import java.util.Queue;

public class Consumer implements Runnable {
	private Queue<Integer> sharedQueue;

	public Consumer(Queue<Integer> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				consume();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void consume() throws InterruptedException {
		synchronized (sharedQueue) {
			if (sharedQueue.isEmpty()) {
				System.out.println("Queue is empty so consumer is waiting...");
				sharedQueue.wait();
				System.out.println("Consumer woke up....");
			}
		}

		System.out.println("Consumed : " + sharedQueue.poll());
		synchronized (sharedQueue) {
			System.out.println("Consumer notifying....");
			sharedQueue.notifyAll();
		}
	}

}
