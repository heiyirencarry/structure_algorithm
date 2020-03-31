package com.test.concurrent;

import java.util.concurrent.TimeUnit;

public class Test9 {

	Object o = new Object();
	void m() {
		synchronized (o) {
			while(true) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " : " + o.toString());
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Test9 t = new Test9();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m();
			}
		}, "thread-1").start();
		
		TimeUnit.SECONDS.sleep(2);
		
		t.o = new Object();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m();
			}
		}, "thread-2").start();
	}
}
