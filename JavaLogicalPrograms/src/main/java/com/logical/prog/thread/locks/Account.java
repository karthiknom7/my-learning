package com.logical.prog.thread.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

	Lock lock = new ReentrantLock();
	private int amt = 10000;
	
	public void deposit(int newAmt){
		amt += newAmt;
	}
	
	public void withDreaw(int newAmt){
		amt -= newAmt;
	}
	
	public int getBalance(){
		return amt;
	}
	
	public static void tranfer(Account acc1, Account acc2, int amt){
		acc1.withDreaw(amt);
		acc2.deposit(amt);
	}
}
