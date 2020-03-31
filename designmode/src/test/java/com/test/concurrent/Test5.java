package com.test.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 线程异常会释放锁
 * @author eBuy
 *
 */
public class Test5 {

	private int i = 0;
	public void m() {
		synchronized (this) {
			while(true) {
				i++;
				System.out.println(Thread.currentThread().getName() + " : " + i);
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(i == 5) {
					i = i/0;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Test5 t = new Test5();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m();
			}
		}).start();
	}
}
