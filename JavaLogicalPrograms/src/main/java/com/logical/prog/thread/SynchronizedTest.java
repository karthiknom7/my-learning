package com.logical.prog.thread;

import java.util.Arrays;

public class SynchronizedTest {

	public static void main(String[] args) throws InterruptedException {

		String[] strArray = { "1", "2", "3", "4", "7" };

		Thread t1 = new Thread(new ArrayProcessor(strArray), "t1");
		Thread t2 = new Thread(new ArrayProcessor(strArray), "t2");
		Thread t3 = new Thread(new ArrayProcessor(strArray), "t3");

		t1.start();
		t2.start();
		t3.start();

		t1.join();
		t2.join();
		t3.join();

		System.out.println(Arrays.toString(strArray));
		;
	}

	public static class ArrayProcessor implements Runnable {

		private String[] strArray;

		public ArrayProcessor(String[] strArray) {
			this.strArray = strArray;
		}

		@Override
		public void run() {
			processArray(Thread.currentThread().getName());
		}

		private void processArray(String name) {
			for (int i = 0; i < strArray.length; i++) {
				processSomething(i);
				// add here
				synchronized (strArray) {
					addThreadName(i, name);
				}
			}
		}

		private void processSomething(int i) {
			try {
				Thread.sleep(i * 100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private void addThreadName(int index, String name) {
			strArray[index] = strArray[index] + ":" + name;
		}
	}
}
