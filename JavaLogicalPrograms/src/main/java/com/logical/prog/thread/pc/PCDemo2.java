package com.logical.prog.thread.pc;

import java.util.LinkedList;
import java.util.Random;

public class PCDemo2 {

	public static void main(String[] args) {
		PCProcessor processor = new PCProcessor();
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

	public static class PCProcessor {

		private LinkedList<Integer> list = new LinkedList<>();
		private static final int LIMIT = 10;
		private Object lock = new Object();

		public void produce() throws InterruptedException {
			int value = 0;
			while (true) {
				synchronized (lock) {
					while (list.size() == LIMIT) {
						lock.wait();
					}
					System.out.println("Produced :" + value);
					list.add(value++);
					lock.notify();
				}
			}
		}

		public void consume() throws InterruptedException {
			Random random = new Random();
			while (true) {
				synchronized (lock) {
					while(list.isEmpty()){
						lock.wait();
					}
					System.out.print("List size : " + list.size());
					int value = list.removeFirst();
					System.out.println("; Consumed Value is : " + value);
					lock.notify();
				}
				Thread.sleep(random.nextInt(1000));
			}
		}
	}
}
