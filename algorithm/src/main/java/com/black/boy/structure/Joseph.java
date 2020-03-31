package com.black.boy.structure;

public class Joseph {

	public static <T> void main(String[] args) {
		Node<Integer> first = null;
		Node<Integer> pre = null;
		
//		for(int i=1; i<41; i++) {
//			Node<Integer> node = new Node<Integer>(i, null);
//			pre.next = node;
//			pre = node;
//		}
		//构建循环链表 1到41个节点
		for(int i=1;i<=41;++i) {
			if(i == 1) {
				first = new Node<Integer>(i, null);
				pre = first;
				continue;
			}
			Node<Integer> node = new Node<Integer>(i, null);
			pre.next = node;
			pre = node;
			if(i == 41) {
				pre.next = first;
			}
		}
		
		int count = 0; //计数
		Node<Integer> n = first;
		Node<Integer> before = null;
//		while(n!=n.next) {
//			count++;
//			if(count == 3) {
//				before.next = n.next;
//				System.out.print(n.data + ",");
//				count = 0;
//				n = n.next;
//			} else {
//				before = n;
//				n = n.next;
//			}
//		}
		
		System.out.println("---------------------");
		Node<Integer> cur = first;
		Node<Integer> pren = null;
		while(!cur.equals(cur.next)) {
			count++;
			if(count == 3) {
				pren.next = cur.next;
				System.out.print(cur.data + ","); //这个是删除后的元素
				count = 0;
				cur = cur.next;
			}else {
				pren = cur;
				cur = cur.next;
			}
		}
		
		
		System.out.println(cur.data);
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
