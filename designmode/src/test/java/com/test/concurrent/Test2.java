package com.test.concurrent;

public class Test2 {
	
	private Object o = new Object();
	
	public synchronized void m1() {
		System.out.println("start m1");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end m1");
	}
	
	public void m2() {
		synchronized (o) {
			System.out.println("start m2");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("end m2");
		}
	}
	
	public void m3() {
		System.out.println("start m3");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end m3");
	}
	
	static class MyThread implements Runnable {
		private int count;
		private Test2 t2;
		MyThread(int count, Test2 t2){
			this.count = count;
			this.t2 = t2;
		}
		@Override
		public void run() {
			if(count == 1) {
				t2.m1();
			}else if(count == 2) {
				t2.m2();
			}else if(count < 0) {
				t2.m3();
			}
		}
	}
	
	public static void main(String[] args) {
		Test2 t = new Test2();
		new Thread(new MyThread(1, t)).start();
		new Thread(new MyThread(2, t)).start();
		new Thread(new MyThread(-1, t)).start();
	}
}
