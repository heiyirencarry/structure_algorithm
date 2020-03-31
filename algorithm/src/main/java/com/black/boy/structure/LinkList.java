package com.black.boy.structure;

import java.util.Iterator;

public class LinkList<T> implements Iterable<T> {

	private Node<T> first;
	private int N;

	public LinkList(){
		N = 0;
	}
	
	public int size() {
		return N;
	}
	public boolean isEmpty() {
		return N == 0;
	}
	
	public void add(T data) {
		Node<T> cur = first;
		Node<T> newNode = new Node<T>(data, null);
		if(isEmpty()) {
			first = newNode;
		} else {
			while(cur.next != null) {
				cur = cur.next;
			}
			cur.next = newNode;
		}
		N++;
	}
	
	public void add(int index, T data) {
		if(index > N) {
			throw new IndexOutOfBoundsException("index>N");
		}
		if(index == 0)
			add(data);
		Node<T> pre = first;
		for(int i=1; i<index;++i) {
			pre = pre.next;
		}
		Node<T> next = pre.next;
		Node<T> newNode = new Node<T>(data, next);
		pre.next = newNode;
		N++;
	}
	
	public void reverse() {
		reverse(this.first);
	}
	
	private Node<T> reverse(Node<T> node) {
		if(node.next == null) {
			//如果是最后一个节点 头节点直接指向
			this.first = node;
			return node;
		}
		Node<T> cur = reverse(node.next);
		cur.next = node;
		node.next = null;
		return node;
	}
	
	public T delete(int index) {
		return null;
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
		return new MIterator();
	}
	
	private class MIterator implements Iterator<T>{
		Node<T> cur = first;
		int index = 0;
		@Override
		public boolean hasNext() {
			return index < N;
		}

		@Override
		public T next() {
			T data = cur.data;
			cur = cur.next;
			index++;
			return data;
		}
	}
	
}
