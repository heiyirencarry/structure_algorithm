package com.black.boy.algorithm;

import java.util.Arrays;

public class Merge {

	public static void main(String[] args) {
		Integer[] arr = {9,5,2,4,6,9,7,8,3,1};
//		sort(arr);
		Comparable[] mergeSort = mergeSort(arr);
		System.out.println(Arrays.toString(mergeSort));
	}
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] arr) {
		sort(arr, 0, arr.length-1);
	}
	
	
	//递归拆分
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] arr, int low, int hig) {
		if(low >= hig)
			return;
		int mid = low + (hig - low)/2;
		sort(arr, low, mid);
		sort(arr, mid+1, hig);
		merge(arr, low, mid, hig);
		
	}
	
	//合并
	@SuppressWarnings("rawtypes")
	public static void merge(Comparable[] arr, int low, int mid, int hig) {
		Comparable[] tempArr = new Comparable[arr.length];
		//定义三个指针变量
		int p1 = low;
		int p2 = mid+1;
		int index = low;
		while(p1 <= mid && p2 <= hig) {
			if(Util.isGreater(arr[p1], arr[p2])) {
				tempArr[index++] = arr[p2++];
			} else {
				tempArr[index++] = arr[p1++];
			}
		}
		while(p1<=mid) {
			tempArr[index++] = arr[p1++];
		}
		while(p2 <= hig) {
			tempArr[index++] = arr[p2++];
		}
		for(int i=low; i<=hig; i++) {
			arr[i] = tempArr[i];
		}
	}
	
	public static Comparable[] mergeSort(Comparable[] arr) {
		if(arr.length < 2){
			return arr;
		}
		
		int mid = arr.length / 2;
		
		Comparable[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid));
		Comparable[] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));
		return arrMerge(left, right);
	}

	private static Comparable[] arrMerge(Comparable[] left, Comparable[] right) {
		Comparable[] arr = new Comparable[left.length + right.length];
		
		for(int i=0,j=0,index=0; index < arr.length; index++) {
			if(i >= left.length) {
				arr[index] = right[j++];
			}else if(j >= right.length) {
				arr[index] = left[i++];
			}else if(Util.isGreater(left[i], right[j])) {
				arr[index] = left[i++]; 
			}else {
				arr[index] = right[j++];
			}
		}
		return arr;
	}
}
