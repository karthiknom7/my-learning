package com.logical.prog.thread.pc;

import java.util.Scanner;

public class PCDemo {

	public static void main(String[] args) {

		Processor processor = new Processor();
		
		Thread producer = new Thread(()-> {
			try {
				processor.produce();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}});
		
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
		System.out.println("Main complted...");
	}
	
	public static class Processor{
		
		public void produce() throws InterruptedException{
			synchronized (this) {
				System.out.println("Producer started ..... ");
				wait();
				System.out.println("Producer resumed....");
			}
		}
		
		public void consume() throws InterruptedException{
			Scanner scanner = new Scanner(System.in);
			Thread.sleep(2000);
			synchronized(this){
				System.out.println("Waiting for return key....");
				scanner.nextLine();
				System.out.println("Return key is pressed.....");
				notify();
				System.out.println("Notify is called. But lock will not release untill sysn block complte");
				Thread.sleep(5000);
			}
		}
	}

}
