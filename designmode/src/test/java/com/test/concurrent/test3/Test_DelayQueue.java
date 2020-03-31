package com.test.concurrent.test3;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Test_DelayQueue {

	public static void main(String[] args) throws InterruptedException {
		DelayQueue<Test> queue = new DelayQueue<Test>();
		long cur = System.currentTimeMillis();
		
		queue.add(new Test(cur + 2000));
		queue.add(new Test(cur + 6000));
		queue.add(new Test(cur + 3000));
		
		for(int i=0; i<5; i++) {
			System.out.println(queue.take());
		}
	}
	
	static class Test implements Delayed {
		private Long time;
		Test(Long time) {
			this.time = time;
		}

		@Override
		public int compareTo(Delayed o) {
			return (int)(this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return unit.convert(time - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		}
		
	}
}
