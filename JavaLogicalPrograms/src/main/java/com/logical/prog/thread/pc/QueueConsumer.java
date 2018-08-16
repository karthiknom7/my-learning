package com.logical.prog.thread.pc;

import java.util.concurrent.BlockingQueue;

public class QueueConsumer implements Runnable{

	private final BlockingQueue<Integer> sharedQueue;
	
	 public QueueConsumer(BlockingQueue<Integer> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}
	@Override
	public void run() {

		while(true){
			try {
				System.out.println("Consumig " + sharedQueue.take());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
