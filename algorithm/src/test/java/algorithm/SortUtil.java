package algorithm;

public class SortUtil {

	public static final Integer[] ARR = {9,5,2,4,6,7,8,3,1};
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean isLarge(Comparable a, Comparable b) {
		return a.compareTo(b) > 0;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isSmall(Comparable a, Comparable b) {
		return !isLarge(a, b);
	}
	
	@SuppressWarnings("rawtypes")
	public static void exchange(Comparable[] arr, int a, int b) {
		Comparable temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
