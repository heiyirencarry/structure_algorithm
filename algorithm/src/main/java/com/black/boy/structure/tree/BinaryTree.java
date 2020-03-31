package com.black.boy.structure.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 二叉树的实现
 * @author eBuy
 *
 * @param <K>
 * @param <V>
 */
public class BinaryTree<K extends Comparable<K>, V> {
	
	//二叉树的节点数量
	private int N;
	
	//二叉树的根节点
	private Node root;
	
	public BinaryTree() {
		N = 0;
		root = null;
	}
	
	public int size() {
		return N;
	}
	
	//添加节点
	public void put (K key, V value) {
		root = put(root, key, value);
	}
	
	//指定节点的子树添加节点
	public Node put(Node node, K key, V value) {
		//node节点为null
		if(node == null) {
			node = new Node(key, value, null, null);
			N++;
			return node;
		}
		
		//如果node点不为null
		int rs = node.key.compareTo(key);
		if(rs > 0) {
			//key比当前的node小放左节点
			node.left = put(node.left, key, value);
		} else if (rs < 0) {
			//key比当前的node大放右节点
			node.right = put(node.right, key, value);
		} else {
			node.value = value;
		}
		return node;
	}
	
	//获取值
	public V get(K key) {
		return get(root, key);
	}
	
	//二叉树中指定节点中key的查找
	public V get(Node node, K key) {
		if(node == null)
			return null;
		int rs = key.compareTo(node.key);
		if(rs>0)
			return get(node.right, key);
		if(rs<0)
			return get(node.left, key);
		return node.value;
	}
	
	//二叉树中获取最小值
	public V getMin() {
		return getMin(root);
	}
	public V getMin(Node node) {
		if(node == null)
			return null;
		while(node.left != null) 
			node = node.left;
		return node.value;
	}
	
	//二叉树获取最大值
	public V getMax() {
		return getMax(root);
	}
	public V getMax(Node node) {
		if(node == null)
			return null;
		while(node.right != null)
			node = node.right;
		return node.value;
	}
	
	//删除节点
	public void delete(K key) {
		delete(root, key);
	}
	
	//指定的子树中删除节点   其实 返回的是一个新的子树的   一个有左右节点的节点就是一个二叉树
	public Node delete(Node node, K key) {
		if(node == null)
			return null;
		
		int rs = key.compareTo(node.key);
		if(rs>0) {
			return delete(node.right, key);
		} else if (rs<0) {
			return delete(node.left, key);
		} else {
			N--; //相等就需要删除
			if(node.right == null)
				return node.left;
			if(node.left == null)
				return node.right;
			//处理左右节点都不为空的删除操作
			//这个时候删除的当前node节点，将右子树中最小的节点找到，并在右子树中删除改最小节点
			//以当前右子树中最小的节点为根节点
			Node m = node.right;
			Node b = null; 
			while(true) {
				if(m.left.left == null) {
					b = m.left;
					m.left = null;
					break;
				}
				m = m.left;
			}
			
			b.left = node.left;
			b.right = node.right;
			return b;
		}
	}
	
	/**
	 * 二叉树的前序遍历
	 * 这个前指的是中间节点的遍历时机 中 左 右
	 * @return
	 */
	public Queue<K> frontErgodic(){
		Queue<K> queue = new LinkedBlockingQueue<K>();
		frontErgodic(root,queue);
		return queue;
	}
	/**
	 *  指定节点的前序遍历
	 * @param node
	 * @param q
	 */
	public void frontErgodic(Node node, Queue<K> q){
		if(node == null)
			return;
		q.add(node.key);
		if(node.left != null) //也可以不判断  少一次函数的压栈
			frontErgodic(node.left, q);
		if(node.right != null) //也可以不判断  少一次函数的压栈
			frontErgodic(node.right, q);
	}
	
	/**
	 * 二叉树的中序遍历
	 * @return
	 */
	public Queue<K> midErgodic() {
		Queue<K> q = new LinkedList<K>();
		midErgodic(root,q);
		return q;
	}
	
	/**
	 * 通过指定节点遍历二叉树子树    中序遍历    左中右
	 * @param node
	 */
	public void midErgodic(Node node, Queue<K> q){
		if(node == null)
			return;
		if(node.left != null)
			midErgodic(node.left, q);
		q.add(node.key);
		if(node.right != null)
			midErgodic(node.right, q);
	}
	
	/**
	 * 二叉树的后续遍历  左右中
	 * @return
	 */
	public Queue<K> afterErgodic() {
		Queue<K> q = new LinkedList<K>();
		afterErgodic(root, q);
		return q;
	}
	
	/**
	 * 指定根节点二叉子树的后续遍历  左右中
	 * @param node
	 * @param q
	 */
	public void afterErgodic(Node node, Queue<K> q) {
		if(node == null)
			return;
		if(node.left != null)
			afterErgodic(node.left, q);
		if(node.right != null)
			afterErgodic(node.right, q);
		q.add(node.key);
	}
	
	/**
	 * 层序遍历  逐层遍历 从上往下从左往右
	 * @return
	 */
	public List<K> levelErgodic(){
		List<K> list = new LinkedList<K>();
		Queue<Node> queue = new LinkedList<Node>();
		if(root == null)
			return null;
		queue.add(root);
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			list.add(n.key);
			if(n.left != null)
				queue.add(n.left);
			if(n.right != null)
				queue.add(n.right);
		}
		return list;
	}
	
	/**
	 * 获取根节点二叉树的最大深度
	 * @return
	 */
	public int getDepth() {
		return getDepth(root);
	}
	
	/**
	 * 获取某一个节点下子树的最大深度
	 * @param node
	 * @return
	 */
	public int getDepth(Node node) {
		if(node == null)
			return 0;
		int l = 0;
		int r = 0;
		if(node.left != null) //少一次方法的压栈
			l = getDepth(node.left);
		if(node.right != null) //少一次方法的压栈
			r= getDepth(node.right);
//		int l = getDepth(node.left);
//		int r = getDepth(node.right);
		return Math.max(l, r) + 1;
	}
	
	//保存数据的节点对象
	class Node  {
		K key;
		V value;
		Node left;
		Node right;
		Node(K key, V value, Node left, Node right) {
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}
}