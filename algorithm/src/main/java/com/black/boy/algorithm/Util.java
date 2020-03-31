package com.black.boy.algorithm;

public class Util {
	
	public static Integer[] ARR = {9,5,2,4,6,7,8,3,1};

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean isGreater(Comparable a, Comparable b) {
		return a.compareTo(b) > 0;
	}
	
	@SuppressWarnings("rawtypes")
	public static void exchenge(Comparable[] arr, int i, int j) {
		if(i == j)
			return;
		Comparable temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
