package com.black.boy.structure.tree.union;

public class Client {

	public static void main(String[] args) {
		//简单实现   union的过程时间复杂度 O[n] 因为每次都需要循环改变
//		UnionSet us = new UnionSet(5);
//		System.out.println(us.getGroupCount());
//		us.union(2, 3);
//		System.out.println(us.getGroupCount());
//		System.out.println(us.connected(2, 3));
//		System.out.println(us.connected(1, 3));
		
//		TreeUnionSet us = new TreeUnionSet(5);
//		System.out.println(us.groupSize());
//		us.union(2, 3);
//		System.out.println(us.groupSize());
//		System.out.println(us.connected(2, 3));
//		System.out.println(us.connected(1, 3));
		
		TreeUnionSetSize us = new TreeUnionSetSize(5);
		System.out.println(us.groupSize());
		us.union(2, 3);
		System.out.println(us.groupSize());
		System.out.println(us.connected(2, 3));
		System.out.println(us.connected(1, 3));
		
	}
}
