package com.test.concurrent.test2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test03 {

	
	Lock lock = new ReentrantLock();
	Condition put = lock.newCondition(); //存储的标记
	Condition get = lock.newCondition(); //获取的标记
	
	List<Integer> list = new ArrayList<Integer>();
	
	void add() {
		try {
			lock.lock();
			for(int i=0; i<10; i++) {
				if(list.size() == 5) {
					get.signalAll();
					put.await();
				}
				list.add(i);
				System.out.println(Thread.currentThread().getName() + ": " + list.size());
			}
		}  catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	void size() {
		
		try {
			lock.lock();
			if(list.size() != 5) {
				get.await();
			}
			System.out.println(list.size());
			put.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		Test03 t = new Test03();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.add(); 
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.size();
			}
		}).start();
	}
}
