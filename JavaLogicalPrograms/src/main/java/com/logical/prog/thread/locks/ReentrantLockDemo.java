package com.logical.prog.thread.locks;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

	public static void main(String[] args) {

		Runner runner = new Runner();

		Thread t1 = new Thread(() -> {
			try {
				runner.firstThread();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		Thread t2 = new Thread(() -> {
			try {
				runner.secondThread();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		runner.finished();
	}

	public static class Runner {
		private int count = 0;
		private Lock lock = new ReentrantLock();
		private Condition cond = lock.newCondition();

		private void increment() {
			for (int i = 0; i < 1000; i++) {
				count++;
			}
		}

		public void firstThread() throws InterruptedException {
			lock.lock();
			System.out.println("Waiting..... ");
			cond.await();
			System.out.println("Woken up!");
			try {

				increment();
			} finally {
				lock.unlock();
				System.out.println("First thread unlocked the lock");
			}
		}

		public void secondThread() throws InterruptedException {
			Thread.sleep(1000);
			lock.lock();
			System.out.println("Press the return key!");
			new Scanner(System.in).nextLine();
			System.out.println("Got return key!");
			cond.signal(); // it is just like notify method of object. This method will not unlock the lock. it just signals waiting threads 
			try {
				increment();
			} finally {
				lock.unlock();
				System.out.println("Second thread unlocked the lock!");
			}
		}

		public void finished() {
			System.out.println("Count is : " + count);
		}
	}
}
