package structure.line;

import java.util.Iterator;

public class QueueLine<T> implements Iterable<T> {

	private int N;
	private Node head;
	
	public QueueLine() {
		N = 0;
		head = new Node(null, null);
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	/**
	 * 进入队列
	 * @param t
	 */
	public void enQueue(T t) {
		head.next = new Node(head.next, t);
		N++;
	}
	
	public T deQueue() {
		if(isEmpty())
			return null;
//		Node pre = null;
//		Node cur = head;
//		while(cur.next != null) {
//			pre = cur;
//			cur = cur.next;
//		}
//		pre.next = null;
//		N--;
//		return cur.item;
		Node cur = head;
		while(cur.next.next != null)
			cur = cur.next;
		T t = cur.next.item;
		cur.next = null;
		N--;
		return t;
	}
	
	class Node{
		Node next;
		T item;
		Node(Node next, T t){
			this.next = next;
			item = t;
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
