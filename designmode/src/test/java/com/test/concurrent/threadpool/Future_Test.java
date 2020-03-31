package com.test.concurrent.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Future_Test {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService pool = Executors.newFixedThreadPool(3);
		
		for(int i=0; i<10; i++) {
			Future<String> submit = pool.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					return Thread.currentThread().getName() + ".......";
				}
			});
			
			System.out.println(submit.get());
		}
	}
}
