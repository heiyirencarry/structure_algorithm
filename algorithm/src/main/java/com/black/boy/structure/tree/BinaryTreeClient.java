package com.black.boy.structure.tree;

import java.util.List;
import java.util.Queue;

public class BinaryTreeClient {

	public static void main(String[] args) {
		BinaryTree<Integer, String> tree = new BinaryTree<Integer, String>();
		tree.put(2, "bb");
		tree.put(1, "aa");
		tree.put(3, "zz");
		tree.put(4, "cc");
		tree.put(5, "nn");
		
		Queue<Integer> q = tree.midErgodic();
		for (Integer key : q) {
			print(tree, key);
		}
		
		tree.delete(3);
		System.out.println(tree.getMax());
		System.out.println(tree.getMin());
		
		
		
//		BinaryTree<String, String> tree = new BinaryTree<String, String>();
//		tree.put("E", "5");
//		tree.put("B", "2");
//		tree.put("G", "7");
//		tree.put("A", "1");
//		tree.put("D", "4");
//		tree.put("F", "6");
//		tree.put("H", "8");
//		tree.put("C", "3");
		
//		Queue<String> q = tree.frontErgodic();
//		for (String key : q) {
//			System.out.println(key + " ————> " + tree.get(key));
//		}
//		List<String> list = tree.levelErgodic();
//		for (String key : list) {
//			print(tree, key);
//		}
		System.out.println(tree.getDepth());
	}
	
	
	
	static <K extends Comparable<K>,V> void print(BinaryTree<K, V> tree, K key) {
		System.out.println(key + " ---> " + tree.get(key));
	}
}
