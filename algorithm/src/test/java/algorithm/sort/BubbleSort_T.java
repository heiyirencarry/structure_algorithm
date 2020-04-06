package algorithm.sort;

import algorithm.SortUtil;

public class BubbleSort_T {

	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] arr) {
		for(int i=1; i<arr.length; i++) {
			for(int j=i; j<arr.length; j++) {
				if(SortUtil.isLarge(arr[j], arr[j-1])) {
					SortUtil.exchange(arr, j, j-1);
				}
			}
		}
	}
}
