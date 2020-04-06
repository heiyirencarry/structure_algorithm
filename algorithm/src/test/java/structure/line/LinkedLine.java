package structure.line;

import java.util.Iterator;

public class LinkedLine<T> implements Iterable<T>{

	private int N;
	private Node head;
	
	public LinkedLine() {
		this.N = 0;
		this.head = new Node(null, null);
	}
	
	/**
	 * 是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return N == 0;
	}
	
	/**
	 * 获取长度
	 * @return
	 */
	public int size() {
		return N;
	}
	
	public void add(T t) {
		Node cur = head;
		while(cur.next != null) {
			cur = cur.next;
		}
		cur.next = new Node(t, null);
		N++;
	}
	
	//0 1 2 3 4 5 6
	public void add(int index, T t) {
		checkIndex(index);
		Node cur = head;
		for(int i=0; i<index; i++) {
			cur = cur.next;
		}
		Node oldNode = cur.next;
		Node newNode = new Node(t, oldNode);
		cur.next = newNode;
		N++;
	}
	
	public T delete() {
		if(isEmpty())
			return null;
		
		Node pre = head;
		Node cur = head.next;
		while(cur.next!= null) {
			pre = cur;
			cur = cur.next;
		}
		pre.next = null;
		N--;
		return cur.item;
	}
	
	public T delete(int index) {
		checkIndex(index);
		
		Node pre = head;
		Node cur = head.next;
		for(int i=0; i<index; i++) {
			cur = cur.next;
			pre = pre.next;
		}
		pre.next = cur.next;
		N--;
		return cur.item;
	}
	
	public T get(int index) {
		checkIndex(index);
		Node cur = head;
		for(int i=0; i<=index; i++) {
			cur = cur.next;
		}
		return cur.item;
	}
	
	public int get(T t) {
		Node cur = head;
		for(int i=0; i<N;i++) {
			cur = cur.next;
			if(cur.item.equals(t)) {
				return i;
			}
		}
		return -1;
	}
	
	private void checkIndex(int index) {
		if(index < 0 || index >= N) {
			throw new IndexOutOfBoundsException("链表line角标越界");
		}
	}
	
	class Node{
		T item;
		Node next;
		Node(T t, Node next){
			this.item = t;
			this.next = next;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node cur = head;
			
			@Override
			public boolean hasNext() {
				return cur.next != null;
			}

			@Override
			public T next() {
				cur = cur.next;
				return cur.item;
			}
		};
	}
	
}
