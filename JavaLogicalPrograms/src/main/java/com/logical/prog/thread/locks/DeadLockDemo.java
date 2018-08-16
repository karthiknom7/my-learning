package com.logical.prog.thread.locks;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockDemo {

	public static void main(String[] args) {

		DLRanner runner = new DLRanner();

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

	public static class DLRanner {
		private Account acc1 = new Account();
		private Account acc2 = new Account();

		/*private Lock lock1 = new ReentrantLock();
		private Lock lock2 = new ReentrantLock();*/

		public void acquireLock(Lock firstLock, Lock secodLock) throws InterruptedException {
			while (true) {
				boolean gotFirstLock = false;
				boolean gotSecondLock = false;
				try {
					gotFirstLock = firstLock.tryLock();
					gotSecondLock = secodLock.tryLock();
				} finally {
					if (gotFirstLock && gotSecondLock) {
						return;
					}

					if (gotFirstLock) {
						firstLock.unlock();
					}

					if (gotSecondLock) {
						secodLock.unlock();
					}
					Thread.sleep(1);
				}
			}
		}

		public void firstThread() throws InterruptedException {
			Random random = new Random();
			/*
			 * lock1.lock(); lock2.lock();
			 */
			acquireLock(acc1.lock, acc2.lock);
			try {
				for (int i = 0; i < 1000; i++) {
					Account.tranfer(acc1, acc2, random.nextInt(100));
				}
			} finally {
				acc1.lock.unlock();
				acc2.lock.unlock();
			}

		}

		public void secondThread() throws InterruptedException {
			Random random = new Random();
			/*
			 * lock2.lock(); lock1.lock();
			 */
			acquireLock(acc1.lock, acc2.lock);
			try {
				for (int i = 0; i < 1000; i++) {
					Account.tranfer(acc2, acc1, random.nextInt(100));
				}
			} finally {
				acc1.lock.unlock();
				acc2.lock.unlock();
			}
		}

		public void finished() {

			System.out.println("Balance in acc1 : " + acc1.getBalance());
			System.out.println("Balance in acc2 : " + acc2.getBalance());
			System.out.println("Total is : " + (acc1.getBalance() + acc2.getBalance()));
		}
	}
}
