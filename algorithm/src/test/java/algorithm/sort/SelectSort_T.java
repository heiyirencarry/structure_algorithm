package algorithm.sort;

import algorithm.SortUtil;

public class SelectSort_T {

	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] arr) {
		for(int i=0; i<arr.length-1; i++) {
			int index = i;
			for(int j=i+1; j<arr.length; j++) {
				if(!SortUtil.isLarge(arr[j], arr[index])) {
					index = j;
				}
				SortUtil.exchange(arr, i, index);
			}
		}
	}
}
