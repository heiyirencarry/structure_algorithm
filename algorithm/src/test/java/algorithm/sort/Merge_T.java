package algorithm.sort;

import algorithm.SortUtil;

public class Merge_T {

	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] arr) {
		sort(arr, 0, arr.length);
	}
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] arr, int start, int end) {
		if(end <= start){
			return;
		}
		int mid = (end - start)/2 + start;
		
		sort(arr, start, mid);
		sort(arr, mid+1, end);
		marge(arr, start, mid, end);
	}
	
	@SuppressWarnings("rawtypes")
	private static void marge(Comparable[] arr, int start, int mid, int end) {
		Comparable[] t = new Comparable[arr.length];
		//定义三个指针
		int l = start;
		int r = mid + 1;
		int index = start;
		
		while(l <= mid && r < end) {
			if(SortUtil.isLarge(arr[l], arr[r])) {
				t[index++] = arr[l++];
			}else {
				t[index++] = arr[r++];
			}
		}
		
		while(l <= mid) {
			t[index++] = arr[l++];
		}
		
		while(r < end) {
			t[index++] = arr[r++];
		}
		
		for(int j=start; j<end; j++) {
			arr[j] = t[j];
		}
	}
}
