package com.black.boy.algorithm;

import java.util.Arrays;

public class Bubble {
	public static void main(String[] args) {
//		Integer[] arr = {9,5,2,4,6,7,8,3,1};
		Integer[] arr = {3,2,1};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] arr) {
		for(int i = arr.length -1; i > 0; i--) {
			for(int j = 0; j < i; j++) {
				if(Util.isGreater(arr[j], arr[j+1]))
					Util.exchenge(arr, j, j+1);
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void sort1(Comparable[] arr) {
		for(int i = 1; i < arr.length; i++) {
			for(int j = 0; j < arr.length - i; j++) {
				if(Util.isGreater(arr[j], arr[j+1]))
					Util.exchenge(arr, j, j+1);
			}
		}
	}
}
