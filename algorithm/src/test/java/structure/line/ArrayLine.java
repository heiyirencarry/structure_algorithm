package structure.line;

import java.util.Iterator;


public class ArrayLine<T> implements Iterable<T> {

	private static final int CAPACITY = 16;
	private int N;
	private T[] nodes;
//	private int relCapacity;  可以用当前的数组长度判断
	
	@SuppressWarnings("unchecked")
	public ArrayLine(int capacity){
		this.N = 0;
		this.nodes = (T[]) new Object[capacity];
	}
	
	public ArrayLine() {
		this(CAPACITY);
	}
	
	public int size() {
		return N;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public void add(T t) {
		ensureCapacity();
		nodes[N++] = t;
	}
	
	// 0   1   2   3   4
	// 1   2   3   4   5   4
	public void add(int index, T t) {
		checkIndex(index);
		ensureCapacity();
		for(int i=N; i>index; i--) {
			nodes[i] = nodes[i-1];
		}
		nodes[index] = t;
		N++;
	}
	
	/**
	 * 删除最后一个元素
	 * @return
	 */
	public T delete() {
		T t = nodes[N--];
		ensureCapacityInterval();
		return t;
	}
	
	/**
	 * 依据角标删除元素
	 * @param index
	 * @return
	 */
	public T delete(int index) {
		checkIndex(index);
		N--;
		T delNode = nodes[index];
		for(int i=index; i<N; i++) {
			nodes[i] = nodes[i + 1];
		}
		ensureCapacityInterval();
		return delNode;
	}
	
	public int get(T t) {
		for(int i=0; i<N; i++) {
			if(nodes[i].equals(t)) {
				return i;
			}
		}
		return -1;
	}
	
	public T get(int index) {
		checkIndex(index);
		return nodes[index];
	}
	
	
	private void checkIndex(int index) {
		if(index <0 || index >= N) {
			throw new IndexOutOfBoundsException("角标越界");
		}
	}
	
	/**
	 * 确保保存元素的数组容量
	 */
	private void ensureCapacity() {
		if(N > nodes.length*0.8) 
			resize(nodes.length << 1);
		
	}
	
	/**
	 * 确保保存元素的数组容量
	 */
	private void ensureCapacityInterval() {
		if(N < nodes.length>>2)
			resize(nodes.length >> 1);
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int size) {
		T[] temp = nodes;//数组
		T[] newArr = (T[])new Object[size];
		System.arraycopy(temp, 0, newArr, 0, N);
//		T[] newArr = Arrays.copyOf(temp, N);
		nodes = newArr;
	}

	public int getArrSize() {
		return nodes.length;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private int index = 0;
			@Override
			public boolean hasNext() {
				return index < N;
			}

			@Override
			public T next() {
				return nodes[index++];
			}
		};
	}
}
