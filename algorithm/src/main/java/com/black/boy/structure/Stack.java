package com.black.boy.structure;

import java.util.Iterator;

public class Stack<T> implements Iterable<T> {
	
	private int N;
	private Node<T> head;
	
	public Stack() {
		this.N = 0;
		this.head = new Node<T>(null, null);
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	//压栈
	public void put(T data) {
		if(isEmpty()) {
			this.head.next = new Node<T>(data, null);
		} else {
			Node<T> cur = head.next;
			head.next = new Node<T>(data, cur);
		}
		N++;
	}
	
	//弹栈
	public T pop() {
		if(isEmpty()) {
			return null;
		}
		Node<T> cur = head.next;
		head.next = cur.next;
		N--;
		return cur.data;
	}
	
	@SuppressWarnings("hiding")
	class Node<T> {
		Node<T> next;
		T data;
		Node(T data, Node<T> next){
			this.data = data;
			this.next = next;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new SIterator();
	}
	private class SIterator implements Iterator<T>{

		private Node<T> cur = head;
		@Override
		public boolean hasNext() {
			return cur.next != null;
		}

		@Override
		public T next() {
			cur = cur.next;
			return cur.data;
		}
		
	}
}