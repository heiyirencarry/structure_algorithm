package structure.heap;

/**
 * 创建一个堆数据结构，依据堆的特性，可以在堆数组一半以前的所有元素下沉即可堆有序
 * 因为一半以后的都是叶子节点没有必要下沉操作
 * @author eBuy
 *
 */
public class HeapSort {
	
	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] source) {
		Comparable[] tg = createHeap(source);
		
		for(int i=tg.length-1; i > 1; i--) {
			exch(tg, 1, i);
			sink(tg, 1, i);
		}
		
		System.arraycopy(tg, 1, source, 0, source.length);
	}
	
	@SuppressWarnings("rawtypes")
	private static Comparable[] createHeap(Comparable[] source) {
		Comparable[] tg = new Comparable[source.length];
		System.arraycopy(source, 0, tg, 1, source.length);
		for(int i=tg.length/2; i>0; i--) {
			sink(tg, i, tg.length -1);
		}
		return tg;
	}
	
	@SuppressWarnings("rawtypes")
	private static void sink(Comparable[] arr, int s, int e) {
		while(2*s <= e) {
			int max = 2*s;
			if(2*s+1 <= e) {
				if(less(arr, 2*s, 2*s+1)) {
					max = 2*s +1;
				}
			}
			if(less(arr, max, s))
				break;
			exch(arr, s, max);
			s = max;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static boolean less(Comparable[] arr, int i, int j) {
		return arr[i].compareTo(arr[j]) < 0;
	}

	@SuppressWarnings("rawtypes")
	private static void exch(Comparable[] arr, int i, int j) {
		Comparable tp = arr[i];
		arr[i] = arr[j];
		arr[j] = tp;
	}
}
