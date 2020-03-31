package com.test.concurrent;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Test8 {

	AtomicInteger count = new AtomicInteger(0);
	
	public void m() {
		for(int i=0; i<10000; i++) {
			count.incrementAndGet();
		}
	}
	
	
	public static void main(String[] args) {
		Test8 t = new Test8();
		ArrayList<Thread> list = new ArrayList<Thread>();
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
		System.out.println(t.count.intValue());
	}
}
