package com.test.concurrent.test3;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Test_LinkedBlockingQueue {

	final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	final Random r = new Random();
	
	public static void main(String[] args) {
		Test_LinkedBlockingQueue t = new Test_LinkedBlockingQueue();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						t.queue.put("value" + t.r.nextInt(1000));
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "producer").start();
		
		for(int i=0; i<3; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
						try {
							System.out.println(Thread.currentThread().getName() + t.queue.take());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}, "consumer" + i).start();
		}
	}
}
