package com.black.boy.structure;

public class LinkListClient {
	public static void main(String[] args) {
		LinkList<String> list = new LinkList<String>();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		list.add(2, "dd");
		list.add("ff");
		for (String s : list) {
			System.out.println(s);
		}
		System.out.println(list.size());
		list.reverse();
		for (String s : list) {
			System.out.println(s);
		}
	}
}
