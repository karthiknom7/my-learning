package com.logical.prog.thread.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		for(int i=0; i< 100; i++){
			executorService.submit(()->{
				Connection.getInstance().connect();
			});
		}
		
		executorService.shutdown();
		
		try {
			if(!executorService.awaitTermination(1, TimeUnit.DAYS)){
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static class Connection{
		private int connections;
		private static Connection instance = new Connection();
		private Semaphore semaphore = new Semaphore(10, true);
		
		private Connection(){
			
		}
		
		public static Connection getInstance(){
			return instance;
		}
		
		public void connect(){
			try {
				semaphore.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try{
			doConnect();
			}finally{
				semaphore.release();
			}
			
			
		}
		
		public void doConnect(){
			
			synchronized(this){
				connections++;
				System.out.println("Number of connections : " + connections);
			}
			
			//do some operation
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			synchronized (this) {
				connections--;
			}
		}
	}
}
