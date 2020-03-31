package com.black.boy.structure.heap;

public class Heap<T extends Comparable<T>> {
	
	//堆中的元素个数
	private int N;
	
	//用来存储元素的数组
	private T[] array;
	
	@SuppressWarnings("unchecked")
	public Heap(int capacity) {
		array = (T[]) new Comparable[capacity + 1];
		N = 0;
	}
	
	/**
	 * 获取堆的当前存储大小
	 * @return
	 */
	public int size() {
		return N;
	}
	
	/**
	 * 堆中插入元素
	 * @param t
	 */
	public void insert(T t) {
		array[++N] = t; //这样数组中的第一个位置没有使用
		swim(N); //新增的这个元素通过上浮算法放到正确的位置
	}
	
	/**
	 * 使用上浮的算法，使索引k处的元素处于堆中正确的位置
	 * 和自己的父节点比较大小，比父节点大就交换位置 k = k/2
	 * @param k
	 */
	private void swim(int k) {
		while(k > 1) {
			if(less(k, k/2))
				exch(k, k/2);
			k = k/2;
		}
	}
	
	/**
	 * 删除堆中最大的元素并返回改元素
	 * @return
	 */
	public T delMax() {
		T max = array[1];//记录最大元素的值
		exch(1, N);//交换最后一个元素和最大一个元素的位置
		array[N] = null;//删除最后一个元素
		N--; //总数减少
		sink(1);//通过下沉的算法是交换后的堆顺序正确
		return max;
	}
	
	/**
	 * 使用下沉的算法，是索引k处的元素处于堆中正确的位置
	 * k处的元素和自己两个子节点中较大者比较，如果比其小则交换位置， k = k*2, k*2+1 为两个子节点
	 * @param k
	 */
	private void sink(int k) {
		/*
		 * 2k是左节点，2k+1是右节点，堆中的数据结构是完全二叉树，所以先有左节点才能有有右节点 
		 */
		while(2*k <= N) { //左节点是存在的
			
			//查询子节点中最大的节点，没有先有左再有右
			int max;
			//判断右节点是否存在
			if(2*k+1 <= N) {
				max = less(k*2, k*2 + 1)? k*2 : k*2 + 1;
			} else { //不存在
				max = k*2;
			}
			
			if(less(k,max))
				break;
			
			exch(k, max);
//			k = k*2; //这里不能是这个原因是取的两个子节点中较大者有可能是右节点 
			k = max; 
		}
	}
	
	/**
	 * 交换i与j索引位置处的元素
	 * @param i
	 * @param j
	 */
	private void exch(int i, int j) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	/**
	 * 大于0 i所在的元素大于j
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean less(int i, int j) {
		return array[i].compareTo(array[j]) > 0;
	}
	
	

}