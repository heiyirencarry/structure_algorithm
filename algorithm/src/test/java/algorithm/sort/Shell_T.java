package algorithm.sort;

import algorithm.SortUtil;

public class Shell_T {
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] arr) {
		int h = 1;
		while(h < arr.length>>2) {
			h = h << 1 + 1;
		}
		
		while(h > 1) {
			for(int i=h; i<arr.length; i++) {
				for(int j=i; j>=h; j-=h) {
					if(SortUtil.isLarge(arr[j], arr[j-h])) {
						SortUtil.exchange(arr, j, j-h);
					}
				}
			}
			h = h >> 1;
		}
	}
}
