package com.test.concurrent;

/**
 * 通知os底层，在cpu计算的过程中都要检查内存中数据的有效性，保证最新的内存数据被使用
 * @author eBuy
 *
 */
public class Test6 {

	/* volatile */ int i = 0;
	
	public void m() {
		System.out.println("start m");
		while(i == 0) {}
		System.out.println("end m");
	}
	
	public static void main(String[] args) throws InterruptedException {
		Test6 t = new Test6();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m();
			}
		}).start();
		Thread.sleep(100);
		t.i = 100;
		System.out.println("stop i = " + t.i);
	}
}
