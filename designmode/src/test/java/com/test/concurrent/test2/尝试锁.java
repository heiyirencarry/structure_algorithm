package com.test.concurrent.test2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 尝试锁
 * @author eBuy
 *
 */
public class 尝试锁 {

	public static void main(String[] args) {
		
		TryLock test = new TryLock();
		new Thread(new Runnable() {
			@Override
			public void run() {
				test.m1();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				test.m2();
			}
		}).start();
		
	}
	
	static class TryLock {
		Lock lock = new ReentrantLock();
		void m1() {
			try {
				lock.lock();
				for(int i=0; i<10; i++) {
					System.out.println("m1: " + i);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} finally {
				lock.unlock();
			}
		}
		
		void m2() {
			boolean tryLock = false;
			try {
//				tryLock = lock.tryLock();
				tryLock = lock.tryLock(10000, TimeUnit.SECONDS);
				if(tryLock) {
					System.out.println("m2 syn");
				} else {
					System.out.println("m2 no syn");
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				if(tryLock)
					lock.unlock();
			}
		}
	}
}
