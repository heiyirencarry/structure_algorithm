package algorithm;

import java.util.Arrays;

import algorithm.sort.BubbleSort_T;
import algorithm.sort.InsertSort_T;
import algorithm.sort.Merge_T;
import algorithm.sort.SelectSort_T;
import algorithm.sort.Shell_T;

public class Client {
	
	public static void main(String[] args) {
		Shell_T.sort(SortUtil.ARR);
		
//		BubbleSort_T.sort(SortUtil.ARR);
//		SelectSort_T.sort(SortUtil.ARR);
//		InsertSort_T.sort(SortUtil.ARR);
//		Shell_T.sort(SortUtil.ARR);
		Merge_T.sort(SortUtil.ARR);
		System.out.println(Arrays.toString(SortUtil.ARR));
	}
}
