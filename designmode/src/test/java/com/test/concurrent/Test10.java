package com.test.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Test10 {

	CountDownLatch latch = new CountDownLatch(5);
	
	void m1() {
		try {
			latch.await();//等待门栓的开放
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		System.out.println("m1 method");
	}
	
	void m2() {
		for(int i=0; i<10; i++) {
			if(latch.getCount() != 0) {
				System.out.println("latch count: " + latch.getCount());
				latch.countDown(); //减门栓上的锁
			}
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.println("m2 method: " + i);
		}
	}
	
	public static void main(String[] args) {
		Test10 t = new Test10();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m1();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m2();
			}
		}).start();
	}
}
