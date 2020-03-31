package com.black.boy.structure.heap.priority;

/**
 * 最小优先队列  堆实现
 * @author eBuy
 *
 */
public class MinPriorityHeap<T extends Comparable<T>> {

	private int N;
	private T[] items;
	
	@SuppressWarnings("unchecked")
	public MinPriorityHeap(int capacity) {
		N = 0;
		items = (T[]) new Comparable[capacity+1];
	}
	
	public int size() {
		return N;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public void add(T data) {
		items[++N] = data;
		swim(N);
	}
	
	private void swim(int k) {
		while(k > 1) {
			if(isLess(k/2, k))
				break;
			exch(k, k/2);
			k = k/2;
		}
	}
	
	public T delMin() {
		T min = items[1];
		exch(1, N);
		N--;
		sink(1);
		return min;
	}
	
//	private void sink(int k) { //这个有点问题
//		while(k<<1 <= N) {
//			int min;
//			if(k<<1+1 <= N)
//				min = isLess(k<<1, k<<1+1)? k<<1:k<<1+1;
//			else
//				min = k<<1;
//			if(isLess(k, min))
//				break;
//			exch(k, min);
//			k = min;
//		}
//	}
	private void sink(int k) {
		while(2*k <= N) {
			int min;
			if(2*k+1 <= N)
				min = isLess(2*k, 2*k+1)? 2*k:2*k+1;
			else
				min = 2*k;
			if(isLess(k, min))
				break;
			exch(k, min);
			k = min;
		}
	}
	
	
	private boolean isMore(int i, int j) {
		return items[i].compareTo(items[j]) > 0;
	}
	
	private boolean isLess(int i, int j) {
		return items[i].compareTo(items[j]) < 0;
	}
	
	private void exch(int i, int j) {
		T tp = items[i];
		items[i] = items[j];
		items[j] = tp;
	}
}
