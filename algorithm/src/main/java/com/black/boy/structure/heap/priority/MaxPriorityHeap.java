package com.black.boy.structure.heap.priority;

/**
 * 最大优先队列 堆数据结构实现
 * @author eBuy
 *
 */
public class MaxPriorityHeap<T extends Comparable<T>> {

	//存储的元素个数
	private int N; 
	
	//存储元素的数组
	private T[] items;
	
	@SuppressWarnings("unchecked")
	public MaxPriorityHeap(int capacity) {
		N = 0;
		items = (T[]) new Comparable[capacity + 1];
	}
	
	/**
	 * 存数元素的个数
	 * @return
	 */
	public int size() {
		return N;
	}
	
	/**
	 * 队列是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return N == 0;
	}
	
	/**
	 * 添加元素
	 * @param data
	 */
	public void add(T data) {
		items[++N] = data; //新增元素先添加到数组末尾然后上浮操作
		swim(N);
	}
	
	/**
	 * 删除最大的元素并返回
	 * @return
	 */
	public T delMax() {
		T max = items[1];//保存最大元素
		exch(1, N); //交换最大元素(索引1)和最后一个元素的位置
		N--; //删除最后一个元素，交换后是最大的元素
		sink(1); //对一个元素下沉操作，是堆数据结构成立
		return max;
	}
	
	/**
	 * 堆k位置的元素做上浮操作 ，就是循环比较当前元素和父节点的元素大小
	 * 如果当前元素大于父节点处的元素则交换位置，直到交换到k元素的正确位置
	 * @param k
	 */
	private void swim(int k) {
		while(k > 1) {
			if(isMore(k/2, k)) 
				break; // 父节点大，到了合适的位置停止循环
			exch(k, k/2); //父节点小需交换位置改变上浮元素的索引继续循环
			k = k/2;
		}
	}
	
	/**
	 * 对堆中k位置的元素做下沉操作，就是循环比较当前元素和两个子节点中最大(在最小优先队列中就找最小的元素了)的元素比较大小
	 * 如果当前元素小于子节点中最大元素则交换两元素的位置，直到正确位置为止
	 * @param k
	 */
	private void sink(int k) {
		while(2*k <= N) {
			int max;//记录子节点中最大索引
			
			if(2*k+1 <= N) 
				max = isMore(2*k, 2*k+1)?2*k:2*k+1;
			else
				max = 2*k;
			
			if(isMore(k, max)) //当前节点比两个子节点中最大的还大是正确的位置结束循环
				break;
			
			exch(k, max); //继续下轮循环
			k = max;
		}
	}
	
	/**
	 * i处的元素是否大于j处的元素
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean isMore(int i, int j) {
		return items[i].compareTo(items[j]) > 0;
	}
	
	/**
	 * i和j索引处的元素交换位置
	 * @param i
	 * @param j
	 */
	private void exch(int i, int j) {
		T temp = items[i];
		items[i] = items[j];
		items[j] = temp;
	}
}
