package com.test.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolTest {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService pool = Executors.newFixedThreadPool(5);
		
		for(int i=0; i< 10; i++) {
			pool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + " : i");
				}
			});
		}
		
		System.out.println(pool.isShutdown());
		System.out.println(pool.isTerminated());
//		
//		pool.shutdown();
//		
//		Thread.sleep(500);
//		System.out.println(pool.isShutdown());
//		System.out.println(pool.isTerminated());
		
		
	}
}
