package com.test.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile 只保证内存可见，不保证同步
 * @author eBuy
 *
 */
public class Test7 {

	volatile int count = 0;
	
	 synchronized void m() {
		for(int i = 0; i < 10000; i++) {
			count++;
		}
	}
	
	public static void main(String[] args) {
		Test7 t = new Test7();
		final List<Thread> list = new ArrayList<Thread>();
		for(int i=0; i<10; i++) {
			list.add(new Thread(new Runnable() {
				@Override
				public void run() {
					t.m();
				}
			}));
		}
		for (Thread thread : list) {
			thread.start();
		}
		for (Thread thread : list) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(t.count);
	}
}
