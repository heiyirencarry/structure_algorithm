package com.test.concurrent.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {

	public static void main(String[] args) {
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);
		
//		for(int i=0; i<10; i++) {
//		pool.scheduleAtFixedRate(new Runnable() {
//			@Override
//			public void run() {
//				System.out.println(Thread.currentThread().getName());
//			}
//		}, 0, 300, TimeUnit.MILLISECONDS);
		pool.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		}, 0, 3000, TimeUnit.MILLISECONDS);
		
		
		System.out.println(pool.isShutdown());
	}
}
