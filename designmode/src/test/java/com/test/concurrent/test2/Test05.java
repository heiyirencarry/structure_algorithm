package com.test.concurrent.test2;

import java.util.concurrent.locks.ReentrantLock;

public class Test05 {

	public static void main(String[] args) {
		LockTest l = new LockTest();
		new Thread(new Runnable() {
			@Override
			public void run() {
				l.m1();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				l.m2();
			}
		}).start();
	}
	
	static class LockTest {
		ReentrantLock lock = new ReentrantLock();
		
		void m1() {
			try {
				lock.lock();
				for(int i=0; i<10; i++) {
					System.out.println(Thread.currentThread().getName() + ": " + i);
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
			try {
				lock.lock();
				System.out.println("m2 run");
			} finally {
				lock.unlock();
			}
		}
		
	}
}


