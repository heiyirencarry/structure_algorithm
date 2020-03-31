package com.test.concurrent.test2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Test04 {

	public static void main(String[] args) {
		TestList t = new TestList();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.put();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.get();
			}
		}).start();
	}
	
}

class TestList {
	
	CountDownLatch latch = new CountDownLatch(1);
	
	List<Integer> list = new ArrayList<Integer>();
	
	void get() {
		if(list.size() != 5) {
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(list.size());
		}
	}
	
	void put() {
		for(int i=0; i<10; i++) {
			list.add(i);
			System.out.println(Thread.currentThread().getName() + ": " + list.size());
			if(list.size() == 5) {
				latch.countDown();
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
