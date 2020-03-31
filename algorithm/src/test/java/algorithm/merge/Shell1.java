package algorithm.merge;

import java.util.Arrays;

import com.black.boy.algorithm.Util;

public class Shell1 {

	public static void main(String[] args) {
//		Integer[] arr = {19,5,2,4,6,9,7,8,3,1};
//		sort(arr);
//		System.out.println(Arrays.toString(arr));
		int[] arr = {19,5,2,4,6,9,7,8,3,1};
		System.out.println(Arrays.toString(ShellSort(arr)));
	}
	
	public static void sort(Integer[] arr) {
		
		int h = 1;
		while(h < arr.length/2) {
			h = 2*h + 1;
		}
		
		while(h >= 1) {
			for(int i=h; i<arr.length; i++) {
				for(int j=i; j>=h; j-=h) {
					if(arr[j-h] > arr[j]) {
						Util.exchenge(arr, j-h, j);
					}else {
						break;
					}
				}
			}
			h = h/2;
		}
	}
	
	
	
	public static int[] ShellSort(int[] array) {
        int len = array.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = array[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = temp;
            }
            gap /= 2;
        }
        return array;
    }
	
	
}
