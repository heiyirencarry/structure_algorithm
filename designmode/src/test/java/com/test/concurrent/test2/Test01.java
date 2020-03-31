package com.test.concurrent.test2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test01<T> {
	
	public static void main(String[] args) {
		Container<Integer> c = new Container<Integer>();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0; i<10; i++) {
					System.out.println(Thread.currentThread().getName() + ": " + i);
					c.add(1);
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					if(c.size() == 5) {
						System.out.println("size == 5");
						break;
					}
				}
			}
		}).start();
	}
}

class Container<T> {
	volatile List<T> list = new ArrayList<T>();
	
	public void add(T t) {
		list.add(t);
	}
	
	public int size() {
		return list.size();
	}
}
