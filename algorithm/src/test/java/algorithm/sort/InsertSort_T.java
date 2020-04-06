package algorithm.sort;

import algorithm.SortUtil;

public class InsertSort_T {

	public static <T> void sort(Comparable<T>[] arr) {
		
		for(int i=1; i<arr.length; i++) {
			//插入排序的精髓就这这里了，i就是分界线，左边排好序的，右边没有排序
			//右边第一个和左边的对比一旦找到合适位置就结束本次循环
			for(int j=i; j>0; j--) { 
				if(!SortUtil.isLarge(arr[j], arr[j-1])) {
					SortUtil.exchange(arr, j, j-1);
				} else {
					break;
				}
			}
		}
		
	}
}
 