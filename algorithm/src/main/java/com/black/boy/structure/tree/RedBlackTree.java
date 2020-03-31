package com.black.boy.structure.tree;

/**
 * 红黑树呃实现
 * @author eBuy
 *
 * @param <K>
 * @param <V>
 */
public class RedBlackTree<K extends Comparable<K>, V> {

	//节点之间链接颜色的标识  红 和 黑
	public static final String RED = "red"; 
	public static final String BLACK = "black";
	
	//红黑树中节点的数量
	private int N;
	//根节点
	private Node root;
	
	public RedBlackTree() {
		N = 0;
		root = null;
	}
	
	/**
	 * 获取树中元素节点数量
	 * @return
	 */
	public int size() {
		return N;
	}
	
	/**
	 * 整个树中元素的添加
	 * @param key
	 * @param value
	 */
	public void put(K key, V value) {
		root = add(root, key, value);
	}
	
	/**
	 * 指定节点为根，插入元素，插入元素后会有平衡的过程，返回新的树的根节点，相当于返回了一个新的子树
	 * @param n
	 * @param key
	 * @param value
	 * @return
	 */
	private Node add(Node n, K key, V value) {
		if(n == null) {
			N++;
			//如果为空，说明是2个节点，不是3个节点，依据2-3树的插入，这个节点为3个节点为红色
			return new Node(key, value, null, null, RED);
		}
		int rs = key.compareTo(n.key);
		if(rs > 0)
			n.right = add(n.right, key, value);
		else if(rs < 0)
			n.left = add(n.left, key, value);
		else
			n.item = value;
		
		/*
		 * 和普通二叉树不同的地方就是需要判断是否需要旋转平衡红黑树，左旋、右旋、颜色反转
		 */
		n = balanceTree(n);
//		if(isRed(n.left) && isRed(n.left.left)) { //右旋
//			n = rotateRight(n);
//		}
//		
//		if(isRed(n.right) && !isRed(n.left)) { //左旋
//			n = rotateLeft(n);
//		}
//		
//		if(isRed(n.left) && isRed(n.right)) { //颜色反转
//			flipColor(n);
//		}
		
		return n;
	}
	
	/**
	 * 对节点n的左节点，右节点、左节点的左节点判断链接的颜色是否需要平衡处理，并返回新的节点
	 * @param n
	 * @return
	 */
	private Node balanceTree(Node n) {
		if(isRed(n.right) && !isRed(n.left)) 
			n = rotateLeft(n);
		if(isRed(n.left) && isRed(n.left.left))
			n = rotateRight(n);
		if(isRed(n.right) && isRed(n.left))
			flipColor(n);
		return n;
	}
	
	/**
	 * 左旋,当前节点的有链接是红链接
	 * @param n
	 * @return
	 */
	private Node rotateLeft(Node n) {
		//记录当前节点的右节点
		Node r = n.right;
		n.right = r.left;
		r.left = n;
		r.color = n.color;
		n.color = RED;
		return r;
	}
	
	/**
	 * 右旋    n的左节点和左节点的左节点为红链接
	 * @param n
	 * @return
	 */
	private Node rotateRight(Node n) {
		//节点链接的交换
		//记录当前左节点的左节点
		Node x = n.left;
		//x的right变为n的left
		n.left = x.right;
		x.right = n;
		x.color = n.color;
		n.color = RED;
		//这个时候x的左右链接都是红色，颜色的反转
		flipColor(x);
		return x;
	}
	
	/**
	 * 颜色的反转，相当于完成4个节点的拆分，这个链接是没有变动的所以不需要返回新的Node
	 * @param n
	 */
	private void flipColor(Node n) {
		n.left.color = BLACK;
		n.right.color = BLACK;
		n.color = RED;
	}
	
	/**
	 * 指向该节点的链接是红色吗
	 * @param node
	 * @return
	 */
	private boolean isRed(Node node) {
		if(node == null)
			return false;
		return RED.equals(node.color);
	}
	
	/**
	 * 整个树中通过key查询值
	 * @param key
	 * @return
	 */
	public V get(K key) {
		return get(root, key);
	}
	
	/**
	 * 在指定的节点的子树中查找key对应的值
	 * @param node
	 * @param key
	 * @return
	 */
	private V get(Node node, K key) {
		if(node == null)
			return null;
		int rs = key.compareTo(node.key);
		if(rs > 0)
			return get(node.right, key);
		else if(rs < 0)
			return get(node.left, key);
		else
			return node.item;
	}
	
	//红黑树的节点设计
	private class Node{
		K key;
		V item;
		Node left;
		Node right;
		String color;
		Node(K key, V item, Node left, Node right, String color){
			this.key = key;
			this.item = item;
			this.left = left;
			this.right = right;
			this.color = color;
		}
	}
}
