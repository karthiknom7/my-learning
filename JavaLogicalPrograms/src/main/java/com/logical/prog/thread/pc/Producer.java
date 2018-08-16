package com.logical.prog.thread.pc;

import java.util.Queue;

public class Producer implements Runnable {

	private Queue<Integer> sharedQueue;
	private int maxSize;

	public Producer(Queue<Integer> sharedQueue, int maxSize) {
		this.sharedQueue = sharedQueue;
		this.maxSize = maxSize;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				produce(i);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void produce(int i) throws InterruptedException {
		synchronized (sharedQueue) {
			if (maxSize == sharedQueue.size()) {
				System.out.println("Queue is full so producer is waiting...");
				sharedQueue.wait();
				System.out.println("Producer woke up....");

			}
		}
		System.out.println("Producing : " + i);
		sharedQueue.add(i);
		synchronized (sharedQueue) {
			System.out.println("Producer notifying...");
			sharedQueue.notifyAll();
		}

	}
}
