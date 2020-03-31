package com.black.boy.structure;

import java.util.Iterator;

public class Queue<T> implements Iterable<T>{

	private int N;//队列中的元素数量
	private Node<T> head;//链表实现队列，这个是头节点
	private Node<T> last;//队列的尾部
	
	
	public Queue() {
		N = 0;
		head = new Node<T>(null, null, null); //头节点默认是null
		last = new Node<T>(null, null, head); //最后节点默认也是null
		head.next = last;
	}
	
	/**
	 * 获取队列中的元素个数
	 * @return
	 */
	public int size() {
		return N;
	}
	
	/**
	 * 判断队列是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return N == 0;
	}
	
	/**
	 * 队列的头部添加元素
	 * @param t
	 */
	public void entryQueue(T t) {
		Node<T> next = head.next;
		Node<T> newNode = new Node<T>(t, next, head);
		head.next.prior = newNode;//很重要不能忘记
		if(last.prior == head)
			last.prior = newNode;
		head.next = newNode;
		N++;
	}
	
	/**
	 * 队列的尾部取出一个元素
	 * @return
	 */
	public T quitQueue() {
		if(isEmpty())
			return null;
		T t = last.prior.item;
		last.prior = last.prior.prior;
		N--;
		return t;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new QIterator();
	}
	
	private class QIterator implements Iterator<T> {
		
		Node<T> curNode = head;
	    int index = 0;
		
		@Override
		public boolean hasNext() {
			return index < N;
		}

		@Override
		public T next() {
			curNode = curNode.next;
			index++;
			return curNode.item;
		}
	}
	
	@SuppressWarnings("hiding")
	class Node<T> {
		Node<T> next;
		Node<T> prior;
		T item;
		Node(T item, Node<T> next, Node<T> prior){
			this.item = item;
			this.next = next;
			this.prior = prior;
		}
	}
	
}
