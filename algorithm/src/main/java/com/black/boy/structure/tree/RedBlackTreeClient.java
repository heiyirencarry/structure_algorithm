package com.black.boy.structure.tree;

public class RedBlackTreeClient {
	public static void main(String[] args) {
		RedBlackTree<Integer, String> rb = new RedBlackTree<Integer, String>();
		rb.put(1, "a");
		rb.put(2, "b");
		rb.put(3, "c");
		for(int i=1; i<4; i++)
			System.out.println(rb.get(i));
	}
}
