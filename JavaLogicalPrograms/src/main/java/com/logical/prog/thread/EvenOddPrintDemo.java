package com.logical.prog.thread;

public class EvenOddPrintDemo {

	public static void main(String[] args) {

		EvenOddPrint evenOddPrint = new EvenOddPrint();

		Thread oddThread = new Thread(() -> {
			try {
				evenOddPrint.printOdd();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		Thread evenThread = new Thread(() -> {
			try {
				evenOddPrint.printEven();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		oddThread.start();
		evenThread.start();

		try {
			oddThread.join();
			evenThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done!");
	}

	public static class EvenOddPrint {
		private int count = 1;
		private int limit = 100;

		public void printEven() throws InterruptedException {
			while (count <= limit) {
				synchronized (this) {
					while (!isEven()) {
						wait();
					}
					System.out.println("Even thread: " + count);
					count++;
					notify();
					Thread.sleep(500);
				}
			}
		}

		public void printOdd() throws InterruptedException {
			while (count < limit) {
				synchronized (this) {
					while (isEven()) {
						wait();
					}
					System.out.println("Odd thread:  " + count);
					count++;
					notify();
					Thread.sleep(500);
				}
			}
		}

		private boolean isEven() {
			return ((count % 2) == 0);
		}
	}
	
	public static class MyStockObj{
	    private double totalPrice;
	    private int tickCount;
	    
	    
		public double getTotalPrice() {
			synchronized (this) {
				return totalPrice;
			}
		}
		public void addPrice(double totalPrice) {
			synchronized (this) {
				this.totalPrice += totalPrice;
			}
		}
		
		public int getTickCount() {
			synchronized (this) {
				return tickCount;
			}
		}
		
		public double findAvg() {
			synchronized (this) {
				return totalPrice / tickCount;
			}
		}
		
	    
	}

}
