package com.black.boy.structure;

public class QueueClient {

	public static <T> void main(String[] args) {
		Queue<String> queue = new Queue<String>();
		queue.entryQueue("A");
		queue.entryQueue("B");
		queue.entryQueue("C");
		queue.entryQueue("D");
		queue.entryQueue("E");
		System.out.println(queue.quitQueue());
		System.out.println(queue.quitQueue());
		System.out.println(queue.quitQueue());
		System.out.println(queue.quitQueue());
		System.out.println(queue.quitQueue());
		System.out.println(queue.quitQueue());
		System.out.println(queue.quitQueue());
		for (String s : queue) {
			System.out.println(s);
		}
	}
}
