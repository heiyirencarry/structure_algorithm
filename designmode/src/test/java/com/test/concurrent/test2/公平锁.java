package com.test.concurrent.test2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class 公平锁 {

	public static void main(String[] args) {
		new Test().start();
		new Test().start();
	}
	
	static class Test extends Thread {
		static Lock lock = new ReentrantLock(true); //公平锁 这个锁必须唯一 注意调用的时候是new关键字
		@Override
		public void run() {
			for(int i=0; i<5; i++) {
				try {
					lock.lock();
					System.out.println(Thread.currentThread().getName() + ": get Lock");
				}finally {
					lock.unlock();
				}
			}
		}
	}
}
