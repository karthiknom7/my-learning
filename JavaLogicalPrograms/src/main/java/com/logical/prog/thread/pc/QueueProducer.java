package com.logical.prog.thread.pc;

import java.util.concurrent.BlockingQueue;

public class QueueProducer implements Runnable{

	private final BlockingQueue<Integer> sharedQueue;
	
	public QueueProducer(BlockingQueue<Integer> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}
	@Override
	public void run() {

		for(int i =0 ;i< 20;i++){
			System.out.println("Producing " + i);
			try {
				sharedQueue.put(i);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
