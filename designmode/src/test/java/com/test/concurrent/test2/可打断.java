package com.test.concurrent.test2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class 可打断 {

	public static void main(String[] args) {
		Test test = new Test();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				test.m1();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				test.m2();
			}
		});
		t1.start();
		t2.start();
		t2.interrupt();
	}
	
	static class Test {
		Lock lock = new ReentrantLock();
		
		void m1() {
			try {
				lock.lock();
				for(int i=0; i<10; i++) {
					System.out.println("m1: " + i);
					Thread.sleep(500);
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				lock.unlock();
			}
		}
		
		void m2() {
			try {
				lock.lockInterruptibly();//阻塞等待锁，可以被其它线程打断阻塞状态
//				lock.lock();
				System.out.println("m2 run");
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				lock.unlock();
			}
		}
	}
}
