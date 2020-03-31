package algorithm.merge;

import java.util.Arrays;

public class Merge2 {

	public static void main(String[] args) {
		int[] arr = {19,5,2,4,6,9,7,8,3,1};
		
		int[] c = Arrays.copyOfRange(arr, 1, 2);//int from, int to 含头不含尾
		System.out.println(Arrays.toString(c));
		
//		int[] mergeSort = mergeSort(arr);
//		System.out.println(Arrays.toString(mergeSort));
	}
	
	public static int[] mergeSort(int[] arr) {
		if(arr.length < 2) {
			return arr;
		}
		
		int mid = arr.length / 2;
		int[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid));
		int[] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));
		return merge(left,  right);
	}

	private static int[] merge(int[] left, int[] right) {
		int[] arr = new int[left.length + right.length];
		
		for(int i = 0,j = 0,index = 0; index < arr.length; index++) {
			if(i >= left.length) { //左边数组取值完事
				arr[index] = right[j++];
			} else if (j >= right.length) { //右边数组取值完事
				arr[index] = left[i++];
			} else if (left[i] > right[j]) { 
				arr[index] = right[j++];
			} else {
				arr[index] = left[i++];
			}
		}
		return arr;
	}
}
