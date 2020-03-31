package com.test.concurrent.test2;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class 生产消费者 {

	public static void main(String[] args) {
		Test<Integer> test = new Test<Integer>();
		for(int i=0; i<10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for(int j=0; j<5; j++)
						test.put(j);
				}
			}, "product: " + i).start();
		}
		
		for(int i=0; i<10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for(int j=0; j<5; j++) {
						test.get();
					}
				}
			}, "cosumer: " + i).start();
		}
	}
	
	static class Test<E> {
		
		LinkedList<E> list = new LinkedList<E>();
		
		Lock lock = new ReentrantLock();
		Condition product = lock.newCondition();
		Condition cosumer = lock.newCondition();
		
		public void put(E e) {
			try {
				lock.lock();
				while(list.size() > 10) {
					try {
						product.await();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				list.add(e);
				System.out.println(Thread.currentThread().getName() + ": " + e);
				cosumer.signalAll();
			}finally {
				lock.unlock();
			}
		}
		
		public void get() {
			try {
				lock.lock();
				while(list.isEmpty()) {
					try {
						cosumer.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				E e = list.removeFirst();
				System.out.println(Thread.currentThread().getName() + ": " + e);
				product.signalAll();
			}finally {
				lock.unlock();
			}
		}
	}
}
