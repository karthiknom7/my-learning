package com.logical.prog.thread;

import java.util.Random;

public class InterruptedDemo {

	public static void main(String[] args) throws InterruptedException {

		
		Thread t1 = new Thread(()->{
			System.out.println("Started....");
			Random random = new Random();
			for(int i=0; i< 1E5; i++){
			/*	if(Thread.currentThread().isInterrupted()){
					System.out.println("Thread is interrupted!");
					break;
				}*/
				
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					System.out.println("Thread interrupted!");
					break;
				}
				Math.sin(random.nextDouble());
			}
			System.out.println("Finished.....");
		});
		
		t1.start();
		
		Thread.sleep(1000);
		t1.interrupt();
		
		t1.join();
		
		
	}

}
