package com.black.boy.structure.heap.priority;

public class IndexPriorityMinHeap<T extends Comparable<T>> {
	
	//堆中的元素数量
	private int N;
	
	/**
	 * eg
	 * 原数据数组   
	 * 保存原数据索引的数组，这个数组要求堆结构，对比原数据数组中的元素保存的是原数据数组中的索引
	 * 保存indexHeap的逆序，indexHeap的值作为索引，indexHeap的索引为值
	 * 
	 *   0    1  2  3  4  5  6
	 * [null, F, E, C, D, A, B] items
	 * 
	 * [null, A, C, B, F, D, E] 堆有序的原数据
	 * 
	 *   0    1  2  3  4  5  6
	 * [null, 5, 3, 6, 1, 4, 2] indexHeap
	 * 
	 *   0    1  2  3  4  5  6  其实就是元数组中的索引
	 * [null, 4, 6, 2, 5, 1, 3] indexItems
	 * 
	 */

	//原元素的数组保存的数据是不变动的
	private T[] items;
	
	/*
	 * 数组中保存的是元素数组中的索引，堆数据构建的时候直接交换的是该数据中的角标，原元素数组不动 
	 */
	private int[] indexHeap;
	
	/*
	 * 逆序indexHeap，逆序的意思是将indexHeap中的索引为值，值为索引保存到新的数组中
	 * 这个逆序索引数组的作用是：当需要修改堆中的任意一个元素的时候(最大最小堆调整的都是第一个元素要不是最大的要不是最小的)
	 * 我们可以通过原数据数组知道修改元素的索引然后遍历indexHeap找到修改原数据数组的索引得到要调整堆的索引
	 *   0    1  2  3  4  5  6
	 * [null, 5, 3, 6, 1, 4, 2] 遍历这个数组找元数组中的索引找到上面的索引值进行堆调整，因为这里调整的是原数据的索引
	 * 如果数据较大的情况这样的遍历是比较耗时的所以新增一个辅助数据堆的逆序索引，逆序索引中的值是indexHeap的索引，值是元数组的索引
	 * 所以知道元数组中的索引在逆序数组中一次查找就能知道堆数组中的原数据索引保存的位置索引
	 */
	private int[] reverseIndexHeap;
	
	@SuppressWarnings("unchecked")
	public IndexPriorityMinHeap(int capacity) {
		N = 0;
		items = (T[]) new Comparable[capacity+1];
		indexHeap = new int[capacity+1];
		reverseIndexHeap = new int[capacity+1];
		
		//堆中的所有保存元数组的索引都初始化为-1
		//为判断元数组中元素索引是否存在提供条件
		for(int i=0; i< reverseIndexHeap.length; ++i)
			reverseIndexHeap[i] = -1;
	}
	
	public int size() {
		return N;
	}
	
	/**
	 * 堆是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return N == 0;
	}
	
	/**
	 * 判断元数组中的索引是否存在
	 * @param index
	 * @return
	 */
	public boolean contains(int index) {
		return reverseIndexHeap[index] != -1;
	}
	
	public void add(int index, T t) {
		//保存到元数组的所用存在直接结束
		if(contains(index))
			return;
		N++; 
		items[index] = t; //元数组元素的保存
		indexHeap[N] = index; //堆数组添加到最后一个位置
		reverseIndexHeap[index] = N; //逆序数组中添加元素
		swim(N); //最后一个元素的上浮操作
	}
	
	/**
	 * 删除最小的元素
	 * @return 返回索引处元素的值
	 */
	public T delMin() {
		int min = indexHeap[1];  //元数组中最小元素的索引
		T itemM = items[min];    //元数组中最小元素的值
		exch(1, N); //交换最小元素和最后一个元素 堆数组中
		reverseIndexHeap[N] = -1; //删除关联的数组  因为需要删除的元素交换到了N
		indexHeap[N] = -1; //删除堆数组中交换到最后面的元素
		items[min] = null; //删除元数组中的最小元素
		N--;
		sink(1); //现有的堆交换后的第一个元素下沉操作
		return itemM;
	}
	
	/**
	 * 删除最小元素
	 * @return 返回对应的索引
	 */
	public int delIndexMin() {
		int min = indexHeap[1];
		exch(1, N);
		reverseIndexHeap[N] = -1;
		indexHeap[N] = -1;
		items[min] = null;
		N--;
		sink(1);
		return min;
	}
	
	/**
	 * 删除元数组中k索引处的元素
	 * @param k
	 * @return
	 */
	public T delete(int k) {
		T t = items[k];
		int index = reverseIndexHeap[k]; //获取的是堆数组的索引，不是值，而逆序中保存的值就是索引，逆序中的索引是堆中的值，对数组的值保存的是元数组的索引
		exch(index, N);
		
		reverseIndexHeap[indexHeap[N]] = -1; //因为需要删除的元素交换到了N处
		indexHeap[N] = -1;
		items[k] = null;
		
		N--;
		
		swim(index);
		sink(index);
		return t;
	}
	
	/**
	 * 交换元数组中k位置的元素为data
	 * @param k
	 * @param data
	 */
	public void exchItem(int k, T data) {
		items[k] = data;
		int index = reverseIndexHeap[k];
		swim(index);
		sink(index);
	}
	
	/**
	 * 堆数组中k位置的元素下沉操作
	 * @param k
	 */
	private void sink(int k) {
		while(2*k <= N) {
			int min;
			if(2*k+1 <= N) 
				min = less(2*k, 2*k+1)? 2*k:2*k+1;
			else
				min = 2*k;
			if(less(k, min))
				break;
			exch(k, min);
			k = min;
		}
	}
	
	/**
	 * 最小堆的上浮实现，最终索引为1的位置保存的是最小元素
	 * @param k
	 */
	private void swim(int k) {
		while(k > 1) {
			if(less(k/2, k))  //父节点小正确位置了，不需要在上浮了
				break;
			exch(k, k/2);
			k = k/2;
		}
	}
	
	/**
	 * 这个交换的是堆数组的数据，传递的参数是堆数据的索引
	 * @param i
	 * @param j
	 */
	private void exch(int i, int j) {
		int tmp = indexHeap[i];
		indexHeap[i] = indexHeap[j];
		indexHeap[j] = tmp;
		
		//逆序中的位置也需跟着堆中的变化而变化
		//i和j为值换了位置，索引也跟着交换  reverseIndexHeap保存的是堆数组的索引，而值是堆的索引
		reverseIndexHeap[indexHeap[i]] = j;
		reverseIndexHeap[indexHeap[j]] = i;
	}
	
	/**
	 * 比较堆数组中索引为i和j处元素的大小，i比j处的小返回true,这个方法是留个堆上浮和下沉使用的不对外暴露
	 * @param i 这两个参数是堆数组中的索引
	 * @param j
	 * @return
	 */
	private boolean less(int i, int j) {
//		return items[indexHeap[i]].compareTo(items[indexHeap[j]]) < 0;
		return items[indexHeap[j]].compareTo(items[indexHeap[i]]) > 0;
	}
	
	
}
