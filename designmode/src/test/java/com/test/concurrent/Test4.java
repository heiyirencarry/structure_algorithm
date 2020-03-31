package com.test.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 相当于锁的重入
 * @author eBuy
 *
 */
public class Test4 {

	public synchronized void m() {
		System.out.println("p m start");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("p m end");
	}
	
	public static void main(String[] args) {
		new Sub_Test4().m();
	}
}

class Sub_Test4 extends Test4{
	
	@Override
	public synchronized void m() {
		System.out.println("s m start");
		super.m();
		System.out.println("s m end");
	}
}
