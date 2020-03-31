package com.test.concurrent;

public class Test1 {

	private Object o = new Object();
	
	public void m1() {
		synchronized(this) {
			System.out.println("method m1");
		}
	}
	
	public synchronized void m2() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("method m2");
	}
	
	public void m3() {
		synchronized (o) {
			System.out.println("method m3");
		}
	}
	
	public static void main(String[] args) {
		Test1 t = new Test1();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m2();
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m3();
			}
		}).start();
	}
}
