package com.test.concurrent.test3;

import java.util.concurrent.ConcurrentHashMap;

public class Test_ConcurrentHashMap {

	public static void main(String[] args) {
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
		String put = map.put("haha", "java");
		String put2 = map.put("haha", "c");
		System.out.println(put);
		System.out.println(put2);
	}
}
