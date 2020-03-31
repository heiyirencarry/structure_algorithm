package algorithm.merge;

import java.util.Arrays;

public class Merge1 {

	public static void main(String[] args) {
		int[] arr = {19,5,2,4,6,9,7,8,3,1};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	
	public static void sort(int[] arr) {
		sort(arr, 0, arr.length - 1);
	}
	
	public static void sort(int[] arr, int low, int hig) {
		if(low >= hig)
			return;
		
		int mid = low + (hig - low) / 2;
		sort(arr, low, mid);
		sort(arr, mid+1, hig);
		merge(arr, low, mid, hig);
	}


	/**
	 * 传递低中高三个参数就是为了划分数组
	 * @param arr
	 * @param low
	 * @param mid  不传重新计算也是可以的，不是说不行
	 * @param hig
	 */
	private static void merge(int[] arr, int low, int mid, int hig) {
		int[] temp = new int[arr.length];
		
		int left = low;
		int riht = mid + 1;
		int index = low;
		
		while(left <= mid && riht <= hig) {
			if(arr[left] > arr[riht]) {
				temp[index++] = arr[riht++];
			} else {
				temp[index++] = arr[left++];
			}
		}
		
		while(left <= mid) {
			temp[index++] = arr[left++];
		}
		
		while(riht <= hig) {
			temp[index++] = arr[riht++];
 		}
		
		for(int i=low; i<=hig; i++) {
			arr[i] = temp[i];
		}
	}
}
