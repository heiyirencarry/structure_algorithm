package com.black.boy.structure.heap;

import java.util.Arrays;

public class HeapClient {
	
	public static void main(String[] args) {
//		Heap<String> heap = new Heap<String>(10);
//		heap.insert("A");
//		heap.insert("B");
//		heap.insert("C");
//		heap.insert("D");
//		heap.insert("E");
//		heap.insert("F");
//		heap.insert("G");
//		
//		String rs = null;
//		while((rs = heap.delMax()) != null) {
//			System.out.println(rs);
//		}
		String[] arr = {"D","C","A","B","E"};
		HeapSort.sort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
