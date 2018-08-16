package com.logical.prog.thread.locks;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PCDemoWithLocks {

	public static void main(String[] args) {

		RLProcessor processor = new RLProcessor();
		
		Thread producer = new Thread(()->{
			try {
				processor.produce();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		Thread consumer = new Thread(()->{
			try {
				processor.consume();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	
		producer.start();
		consumer.start();
		
		try {
			producer.join();
			consumer.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	public static class RLProcessor {

		private LinkedList<Integer> list = new LinkedList<>();
		private static final int LIMIT = 10;
		private Lock lock = new ReentrantLock();
		private Condition cond = lock.newCondition();

		public void produce() throws InterruptedException {
			int value = 0;
			while (true) {
				lock.lock();
				try {
					while (list.size() == LIMIT) {
						System.out.println("Producer is waiting....");
						cond.await();
						System.out.println("Producer resumed....");
					}
					System.out.println("Producing : " + value);
					list.add(value++);
					cond.signal();
				} finally {
					lock.unlock();
				}
			}
		}

		public void consume() throws InterruptedException {
			Random random = new Random();
			while (true) {
				lock.lock();
				try {
					while (list.isEmpty()) {
						System.out.println("Consumer is waiting...");
						cond.await();
						System.out.println("Consumer resumed");
					}
					System.out.print("List size: " + list.size());
					int value = list.removeFirst();
					System.out.println(" Consumed : " + value);
					cond.signal();
				} finally {
					lock.unlock();
				}
				Thread.sleep(random.nextInt(1000));
			}
		}
	}

}
