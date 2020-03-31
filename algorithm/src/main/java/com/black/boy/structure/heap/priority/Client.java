package com.black.boy.structure.heap.priority;

public class Client {

	public static void main(String[] args) {
//		MaxPriorityHeap<String> h = new MaxPriorityHeap<String>(10);
//		h.add("F");
//		h.add("E");
//		h.add("C");
//		h.add("D");
//		h.add("A");
//		h.add("B");
//		while(!h.isEmpty()) {
//			String max = h.delMax();
//			System.out.print(max + " ");
//		}
//		MinPriorityHeap<String> h = new MinPriorityHeap<String>(10);
//		h.add("F");
//		h.add("E");
//		h.add("C");
//		h.add("D");
//		h.add("A");
//		h.add("B");
//		while(!h.isEmpty()) {
//			String min = h.delMin();
//			System.out.print(min + " ");
//		}
		
		//测试索引最小优先队列
		IndexPriorityMinHeap<String> queue = new IndexPriorityMinHeap<String>(10);
		queue.add(0, "A");
		queue.add(1, "C");
		queue.add(2, "D");
		
		queue.exchItem(2, "B");
		while(!queue.isEmpty()) {
			String rs = queue.delMin();
			System.out.print(rs + " ");
		}
	}
}
