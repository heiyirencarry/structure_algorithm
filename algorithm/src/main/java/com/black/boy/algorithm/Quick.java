package com.black.boy.algorithm;

import java.util.Arrays;

public class Quick {

	public static void main(String[] args) {
		Integer[] arr = {3,5,9,2};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] arr) {
		int low = 0;
		int hig = arr.length-1;
		sort(arr, low, hig);
	}
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] arr, int low, int hig) {
		if(low >= hig)
			return;
		int mid = partition(arr, low, hig);
		sort(arr, low, mid-1);
		sort(arr, mid+1, hig);
	}
	
	@SuppressWarnings("rawtypes")
	public static int partition(Comparable[] arr, int low, int hig) {
		int left = low;
		int right = hig+1;
		Comparable key = arr[low];
		//切分
		while(true) {
		    //右边指针移动
			while(Util.isGreater(arr[--right], key)) {
//				if(left == right)
				if(right == low)
					break;
			}
			
			//左指针移动
			while(Util.isGreater(key, arr[++left])) {
//				if(left == right)
				if(left == hig)
					break;
			}
			if(left >= right) {
				break;
			}else {
				Util.exchenge(arr, left, right);
			}
		}
		//交互分界值和right值
		Util.exchenge(arr, low, right);//这个地方不能用left 研究研究
		return right;
	}
}
