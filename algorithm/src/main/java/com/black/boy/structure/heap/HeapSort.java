package com.black.boy.structure.heap;

public class HeapSort {

	/**
	 * 对于可比较的数组利用堆的数据结构排序
	 * @param source
	 */
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] source) {
		/*
		 * 简单描述一下堆排序的过程，因为已经是堆的数据结构了，堆中的获取最大值的过程其实就是一个排序的过程
		 * 堆中最大值的获取是，将第一个元素删除，然后将最后一个元素放到第一个元素的位置，然后堆第一个元素使用下沉使堆的最大位置处
		 * 的元素正确，这样重复获取最大值就可以拿到有序倒序的数据
		 * 那么堆排序同理可得，只是堆排序的时候把最大的喝最后一个交换位置，在第一个元素下沉的过程中选定范围不包括最后一个元素即可；
		 * 这样循环直至最后一个元素结束循环，这样堆中的数组就是正序有序的
		 */
		Comparable[] heap = new Comparable[source.length+1]; //堆中的0角标不使用为了方便期间
		createHeap(source, heap); //这就获取到了堆数据结构的数组
		
		int N = heap.length - 1;
		while(N != 1) { //只要不是最后一个元素就循环
			exch(heap, 1, N);//交换位置
			N--;//最后一个是最大值不需要在参数下沉的循环了，减一
			sink(heap, 1, N);
		}
		
		System.arraycopy(heap, 1, source, 0, source.length);//元数组就是有序的了
	}
	
	/**
	 * 通过元数组构建一个堆数组
	 * @param source
	 * @param target
	 */
	@SuppressWarnings("rawtypes")
	private static void createHeap(Comparable[] source, Comparable[] target) {
		System.arraycopy(source, 0, target, 1, source.length);
		
		//堆内存中的元素下沉调整(所有的不是叶子节点的节点操作，从下往上做下沉，叶子节点不需要，因为没有左右节点和其比较大小来做下沉)
		for(int i=(target.length/2); i>0; i--) { //这个步骤很重要，将任意一个数组构建成堆数据结构
			sink(target, i, target.length-1);
		}
	}
	
	/**
	 * 在堆heap中对target元素下沉，到range范围中下沉
	 * @param heap 堆的数组
	 * @param target 目标元素
	 * @param range 范围 结束的位置
	 */
	@SuppressWarnings("rawtypes")
	private static void sink(Comparable[] heap, int target, int range) {
		while(2*target <= range) { //在固定范围中下沉
			
			int max; //用于一次下沉过程中保存最大角标的临时变量
			
			if(2*target+1 <= range) { //子节点有右节点
				max = less(heap, 2*target, 2*target+1)  //找左右子节点中最大的
						? 2*target:2*target+1;
			} else { //没有右节点最大只能是左节点
				max = 2*target;
			}
			
			if(less(heap, target, max)) {//当前节点比子节点中最大的还大，说明下沉到了正确的位置，结束循环
				break;
			}
			
			exch(heap, target, max);
			target = max;
		}
	}
	
	/**
	 * 比较堆数组中i和j位置元素的大小
	 * @param heap
	 * @param i
	 * @param j
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static boolean less(Comparable[] heap, int i, int j) {
		return heap[i].compareTo(heap[j]) > 0;
	}
	
	/**
	 * 交换heap中两个元素的位置
	 * @param heap
	 * @param i
	 * @param j
	 */
	@SuppressWarnings({ "rawtypes"})
	private static void exch(Comparable[] heap, int i, int j) {
		Comparable temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
		
	}
}
