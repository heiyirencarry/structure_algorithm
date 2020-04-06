package structure.tree;

import structure.line.QueueLine;

public class SearchBinaryTree<K extends Comparable<K>, V> {

	private int N;
	private TreeNode root;
	
	public SearchBinaryTree() {
		this.N = 0;
		this.root = null;
	}
	
	public boolean isEmpty() {
		return N==0;
	}
	
	public int size() {
		return N;
	}
	
	/**
	 * 查找二叉树中添加节点
	 * @param key
	 * @param val
	 */
	public void put(K key, V val) {
		this.root = put(root, key, val);
//		N++; //如果替换掉了呢？N就不用++了，所以放在这个位置是不对的
	}
	
	private TreeNode put(TreeNode node, K key, V val) {
		if(node == null) {
			node = new TreeNode(key, val, null, null);
			N++; //放在这个位置是对的，只有新创建node节点的时候才会N++,这个也是递归调用的出口
		}else {
			int rs = node.key.compareTo(key);
			if(rs < 0) {
				node.right = put(node.right, key, val);
			}else if(rs > 0) {
				node.left = put(node.left, key, val);
			}else {
				node.item = val;
			}
		}
		return node;
	}
	
	/**
	 * 通过key查询二叉树中获取对应的值
	 * @param key
	 * @return
	 */
	public V get(K key) {
		return get(root, key);
	}
	
	private V get(TreeNode node, K key) {
		if(node == null)
			return null; 
		int rs = node.key.compareTo(key);
		if(rs > 0)
			return get(node.left, key);
		else if(rs < 0)
			return get(node.right, key);
		else
			return node.item;
	}
	
	/**
	 * 通过key删除查询二叉树中对应的节点
	 * @param key
	 */
	public void delete(K key) {
		root = delete(root, key);
	}
	
	private TreeNode delete(TreeNode node, K key) {
		if(node == null) 
			return null;
		int rs = node.key.compareTo(key);
		if(rs > 0)
			return delete(node.left, key);
		else if(rs < 0)
			return delete(node.right, key);
		else { //这里说明找到了key对应的节点可以N--
			N--;
			if(node.left == null)
				return node.right;
			if(node.right == null)
				return node.left;
			
			//找到右子树中最小的那个节点和删除的节点交换并返回该节点
			TreeNode s = node;
			while(s.left != null) {
				s = s.left;
			}
			s.left = node.left;
			s.right = node.right;
			return s;
		}
	}
	
	/**
	 * 先序遍历
	 * @return
	 */
	public QueueLine<K> preErgodic(){
		if(isEmpty()) {
			return null;
		}
		QueueLine<K> queue = new QueueLine<K>();
		preErgodic(root, queue);
		return queue;
	}
	private void preErgodic(TreeNode node, QueueLine<K> queue) {
		if(node == null)
			return;
		queue.enQueue(node.key);
		preErgodic(node.left, queue);
		preErgodic(node.right, queue);
	}
	
	/**
	 * 中序遍历
	 * @return
	 */
	public QueueLine<K> midErgodic(){
		if(isEmpty()) return null;
		QueueLine<K> q = new QueueLine<K>();
		midErgodic(root, q);
		return q;
	}
	
	private void midErgodic(TreeNode node, QueueLine<K> q) {
		if(node == null)
			return;
		midErgodic(node.left, q);
		q.enQueue(node.key);
		midErgodic(node.right, q);
	}

	/**
	 * 后序遍历
	 * @return
	 */
	public QueueLine<K> behindErgodic(){
		if(isEmpty())
			return null;
		QueueLine<K> q = new QueueLine<K>();
		behindErgodic(root, q);
		return q;
	}
	
	private void behindErgodic(TreeNode node, QueueLine<K> q) {
		if(node == null) return;
		behindErgodic(node.left, q);
		behindErgodic(node.right, q);
		q.enQueue(node.key);
	}

	/**
	 * 层序遍历
	 * @return
	 */
	public QueueLine<K> levelErgodic(){
		if(isEmpty()) return null;
		QueueLine<K> rs = new QueueLine<K>();
		QueueLine<TreeNode> temp = new QueueLine<TreeNode>();
		temp.enQueue(root);
		while(!temp.isEmpty()) {
			TreeNode node = temp.deQueue();
			rs.enQueue(node.key);
			if(node.left != null) temp.enQueue(node.left);
			if(node.right != null) temp.enQueue(node.right);
		}
		return rs;
	}
	
	/**
	 * 查询二叉树中最大的key
	 * @return
	 */
	public K getMax() {
		if(isEmpty()) return null;
		TreeNode max = root;
		while(max.right != null)
			max = max.right;
		return max.key;
	}
	
	/**
	 * 递归查询的方式
	 * @param node
	 * @return
	 */
	@SuppressWarnings("unused")
	private K getMax(TreeNode node) {
		if(node == null) throw new NullPointerException("要查找的节点为null,这是有问题的");
		if(node.right == null)
			return node.key;
		return getMax(node.right);
	}
	
	/**
	 * 查询二叉树中最小的key
	 * @return
	 */
	public K getMin() {
		if(isEmpty()) return null;
		TreeNode min = root;
		while(min.left != null)
			min = min.left;
		return min.key;
	}
	
	/**
	 * 递归查询左子树和右子树那个大记录那个
	 * @return
	 */
	public int getTreeDepth() {
		return getTreeDepth(root);
	}
	
	public int getTreeDepth(TreeNode node) {
		if(node == null)
			return 0;
		int l;
		int r;
		l = getTreeDepth(node.left);
		r = getTreeDepth(node.right);
		return l > r? l+1: r+1;
		
	}
	
	class TreeNode {
		K key;
		V item;
		TreeNode left;
		TreeNode right;
		TreeNode(K key, V item, TreeNode left, TreeNode right){
			this.key = key;
			this.item = item;
			this.left = left;
			this.right = right;
		}
	}
}
