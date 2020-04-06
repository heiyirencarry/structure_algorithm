package structure.line;

import java.util.Iterator;

public class TwoLinkedLine<T> implements Iterable<T> {

	private Node head;
	private Node last;
	private int N;
	
	public TwoLinkedLine(){
		this.head = new Node(null, null, null);
		this.N = 0;
		this.last = null;
	}
	
	public int size() {
		return N;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	/**
	 * 双向链表的末尾添加节点
	 * @param t
	 */
	public void add(T t) {
		if(isEmpty()) {
			Node newNode = new Node(null, head, t);
			head.next = newNode;
			last = newNode;
		} else {
			Node cur = head;
			while(cur.next != null) {
				cur = cur.next;
			}
			Node newNode = new Node(null, cur, t);
			cur.next = newNode;
			this.last = newNode;
		}
		N++;
	}
	
	public void add(int index, T t) {
		checkIndex(index);
		Node cur = head.next;
		for(int i=0; i<index; i++) {
			cur = cur.next;
		}
		Node newNode = new Node(cur, cur.pre, t);
		cur.pre.next = newNode;
		N++;
	}
	
	public T delete() {
		if(isEmpty())
			return null;
		T t = last.item;
		Node cur = last.pre;
		cur.next = null;
		last = cur;
		N--;
		if(isEmpty())
			last = null;
		return t;
	}
	
	public T delete(int index) {
		checkIndex(index);
		
		Node cur = head;
		for(int i=0; i<=index; i++) {
			cur = cur.next;
		}
		cur.next.pre = cur.pre;
		cur.pre.next = cur.next;
		N--;
		if(isEmpty())
			last = null;
		return cur.item;
	}
	
	public T get(int index) {
		checkIndex(index);
		Node cur = head;
		for(int i=0;i<=index; i++) {
			cur = cur.next;
		}
		return cur.item;
	}
	
	public int get(T t) {
		Node cur = head;
		for(int i=0; i<N; i++){
			cur = cur.next;
			if(cur.item.equals(t))
				return i;
		}
		return -1;
	}
	
	private void checkIndex(int index) {
		if(index <0 || index >= N) {
			throw new IndexOutOfBoundsException("双向链表的角标越界");
		}
	}
	
	class Node{
		Node next;
		Node pre;
		T item;
		Node(Node next, Node pre, T t){
			this.next = next;
			this.pre = pre;
			this.item = t;
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
