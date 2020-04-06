package structure.heap;

public class Heap<T extends Comparable<T>>{

	private int N;
	private T[] items;
	
	@SuppressWarnings("unchecked")
	public Heap(int capacity) {
		this.N = 0;
		this.items = (T[]) new Comparable[capacity+1];
	}
	
	public boolean isEmpty() {
		return N==0;
	}
	
	public int size() {
		return N;
	}
	
	public void insert(T t) {
		items[++N] = t;
		swin(N);
	}
	
	/**
	 * 上浮操作，直到索引为1的元素是最大值
	 * @param k
	 */
	public void swin(int k) {
		while(k > 1) {
			if(less(k/2, k)) {
				exch(k/2, k);
			}
			k = k/2;
		}
	}
	
	/**
	 * 第k个元素下沉操作，直到所有元素遍历完成 N
	 * @param k
	 */
	public void sink(int k) {
		while(2*k <= N) {
			int max = 2*k;
			if(2*k+1 <= N) {
				if(less(2*k, 2*k +1)) {
					max = 2*k + 1;
				}
			}
			if(less(max, k)) {
				break;
			}
			exch(k, max);
			k = max;
		}
	}
	
	/**
	 * 删除并返回最大的元素
	 * @return
	 */
	public T delMax() {
		T t = items[1];
		exch(1, N);
		items[N] = null;
		N--;
		sink(1);
		return t;
	}
	
	private boolean less(int i, int j) {
		return items[i].compareTo(items[j]) < 0;
	}
	
	private void exch(int i, int j) {
		T temp = items[i];
		items[i] = items[j];
		items[j] = temp;
	}
}
