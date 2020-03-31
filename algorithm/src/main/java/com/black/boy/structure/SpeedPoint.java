package com.black.boy.structure;

public class SpeedPoint {
	
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
		
		String mid = getMid(a);
		System.out.println(mid);
	}
	
	public static String getMid(Node<String> first){
		//定义两个指针
		Node<String> fast = first;
		Node<String> slow = first;
		
		while(fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow.data;
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
