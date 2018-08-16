package com.logical.prog.thread;

public class ThreadJoinExample {

	public static void main(String[] args) {
		
		Thread t1 = new Thread(new MyThread());
		Thread t2 = new Thread(new MyThread());
		Thread t3 = new Thread(()->{
			System.out.println("Lamda runnable is running : " + Thread.currentThread().getName());
			try {
				Thread.sleep(4000);
				System.out.println(Thread.currentThread().getName() + " Done!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		System.out.println("Running Main thread : " + Thread.currentThread().getName());
		t1.start();
		try {
			t1.join(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		t2.start();
		
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		t3.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Main is done!");
		
	}

}
