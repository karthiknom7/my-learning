package com.logical.prog.thread;

public class MyThread implements Runnable{

	@Override
	public void run() {
		System.out.println(" Running : " + Thread.currentThread().getName());
		try {
			Thread.sleep(4000);
			System.out.println(Thread.currentThread().getName() + " Done!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
