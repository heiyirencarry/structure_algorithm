package com.black.boy.algorithm;

import java.util.Arrays;

public class Shell {

	public static void main(String[] args) {
		sort(Util.ARR);
		System.out.println(Arrays.toString(Util.ARR));
	}
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] arr) {
		int h = 1;
		while(h < arr.length/2) {
			h = 2*h + 1;
		}
//		System.out.println("h["+h+"]");
		while(h >= 1) {
			for(int i=h; i<arr.length; i++) {
				for(int j=i; j>=h; j-=h) {
					if(Util.isGreater(arr[j-h], arr[j])) {
						Util.exchenge(arr, j, j-h);
					} else {
						break;
					}
				}
			}
			h = h/2;
		}
	}
}
