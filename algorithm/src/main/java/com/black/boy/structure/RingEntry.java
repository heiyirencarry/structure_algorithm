package com.black.boy.structure;

public class RingEntry {

	public static void main(String[] args) {
		Node<String> a = new Node<String>("aa", null);
		Node<String> b = new Node<String>("bb", null);
		Node<String> c = new Node<String>("cc", null);
		Node<String> d = new Node<String>("dd", null);
		Node<String> e = new Node<String>("ee", null);
		a.next=b;
		b.next=c;
		c.next=d;
		d.next=e;
		
		//环
		e.next = c;
		
//		boolean circle = isCircle(a);
//		System.out.println(circle);
//		
		/*定义三个指针两个慢的一个快的(是慢指针的二倍),当一个慢的和一个快的重合的时候
		 * 开始另一个慢指针的移动，当两个慢指针相遇的时候就是入口
		*/
		String entry = getEntry(a);
		System.out.println(entry);
	}
	
	public static boolean isCircle(Node<String> node) {
		Node<String> slow = node;
		Node<String> fast = node;
		
		while(fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if(fast.equals(slow))
				return true;
		}
		return false;
	}
	
	public static String getEntry(Node<String> node) {
		Node<String> slow1 = node;
		Node<String> slow2 = null;
		Node<String> fast = node;
		
		while(fast != null && fast.next != null) {
			fast = fast.next.next;
			slow1 = slow1.next;
			
			if(fast.equals(slow1)) {
				slow2 = node;
				continue; //这个不能少啊
			}
			
			if(slow2 != null) 
				slow2 = slow2.next;
			
			if(slow1.equals(slow2))
				break;
		}
		return slow1.data;
	}
	
	static class Node<T> {
		Node<T> next;
		T data;
		Node(T data, Node<T> next){
			this.data = data;
			this.next = next;
		}
	}
}
