package com.test.concurrent.test2;

import java.util.Iterator;

public class Test02 {

	public static void main(String[] args) {
		MyList<Integer> list = new MyList<Integer>();
		
		Object lock = new Object();
		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock) {
					for(int i=0;i < 10; i++) {
						list.add(i);
						System.out.println(Thread.currentThread().getName() + ": " +list.size());
						if(list.size() == 5) {
							try {
								lock.notifyAll();
								lock.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock) {
					if(list.size() != 5) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println(list.size());
					lock.notifyAll();
				}
			}
		}).start();
	}
	
	
}


class MyList<E> implements Iterable<E>{
	
	private int N;
	private Node<E> root;
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public /* synchronized */ void add(E e) {
		Node<E> cur = root;
		Node<E> newNode = new Node<E>(e, null);
		if(isEmpty()) {
			root = newNode;
		} else {
			while(cur.next != null) {
				cur = cur.next;
			}
			cur.next = newNode;
		}
		N++;
	}
	
	public /* synchronized */ int size() {
		return N;
	}
	
	static class Node<E>{
		Node<E> next;
		E item;
		
		Node(E data, Node<E> next) {
			this.next = next;
			this.item = data;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}
	
	class ListIterator implements Iterator<E>{

		Node<E> cur = root;
		int index = 0;
		@Override
		public boolean hasNext() {
			return index < N;
		}

		@Override
		public E next() {
			E e = cur.item;
			cur = cur.next;
			index++;
			return e;
		}
		
	}
	
}