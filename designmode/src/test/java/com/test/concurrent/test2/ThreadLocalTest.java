package com.test.concurrent.test2;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal
 * 就是一个Map。key - 》 Thread.getCurrentThread().  value - 》 线程需要保存的变量。
 * ThreadLocal.set(value) -> map.put(Thread.getCurrentThread(), value);
 * ThreadLocal.get() -> map.get(Thread.getCurrentThread());
 * 内存问题 ： 在并发量高的时候，可能有内存溢出。
 * 使用ThreadLocal的时候，一定注意回收资源问题，每个线程结束之前，将当前线程保存的线程变量一定要删除 。
 * ThreadLocal.remove();
 */
public class ThreadLocalTest {

	
	static ThreadLocal<String> local = new ThreadLocal<String>();
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " : " +local.get());
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				local.set("haha");
				System.out.println(Thread.currentThread().getName() +" : "+local.get());
				local.remove(); //最好做一下删除的处理
				System.out.println(Thread.currentThread().getName() +" : "+local.get());
			}
		}).start();
	}
}
