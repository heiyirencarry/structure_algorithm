package com.black.boy.algorithm;

import java.util.Arrays;

public class Select {
	public static void main(String[] args) {
		Integer[] arr = {9,5,2,4,6,7,8,3,1};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] arr) {
		for(int i = 0; i < arr.length -1; i++) {
			int minIndex = i;
			for(int j = i+1; j < arr.length; j++) {
				if(Util.isGreater(arr[minIndex], arr[j]))
					minIndex = j;
			}
			Util.exchenge(arr, i, minIndex);
		}
	}
}