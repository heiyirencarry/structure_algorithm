package com.black.boy.algorithm;

import java.util.Arrays;

public class Insert {
	
	public static void main(String[] args) {
		sort(Util.ARR);
		System.out.println(Arrays.toString(Util.ARR));
	}
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] arr) {
		for(int i=1; i < arr.length; i++) {
			for(int j=i; j>0; j--) {
				if(Util.isGreater(arr[j-1], arr[j])) {
					Util.exchenge(arr, j, j-1);
				}else {
					break;
				}
			}
		}
	}
}
