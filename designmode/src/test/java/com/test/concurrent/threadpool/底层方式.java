package com.test.concurrent.threadpool;

import java.util.concurrent.Executor;

public class 底层方式  implements Executor{

	@Override
	public void execute(Runnable command) {
		new Thread(command).start();
	}

	public static void main(String[] args) {
		new 底层方式().execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("测试");
			}
		});
	}
	
}
