package com.test.concurrent;

public class Test3 {

	public synchronized void m1() {
		System.out.println("m1 start");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m2();
		System.out.println("m1 end");
	}
	
	public synchronized void m2() {
		System.out.println("m2 start");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m2 end");
	}
	public static void main(String[] args) {
		new Test3().m1();
	}
}
