package com.black.boy.structure.tree.union;

/**
 * 数组树状结构实现的并查集 数组中保存的是该元素所在分组上一级父节点  (当数组的所在位置和父节点的标志位相同的时候就是分组的根节点)
 * @author eBuy
 *
 */
public class TreeUnionSet {

	private int N;
	private int[] eleAndGroup;
	
	public TreeUnionSet(int count) {
		N = count;
		eleAndGroup = new int[N];
		for (int i = 0; i < eleAndGroup.length; i++) {
			eleAndGroup[i] = i;
		}
	}
	
	public int groupSize() {
		return N;
	}
	
	public boolean connected(int q, int p) {
		return find(q) == find(p);
	}
	
	public int find(int q) {
		while(true) {
			if(eleAndGroup[q] == q) { //分组的根节点
				return q;
			}
			q = eleAndGroup[q];
		}
	}
	
	/**
	 * 这样实现合并的时间复杂度为常数阶但是查询分组根节点的时间复杂度最坏的情况为线性阶，为了使最坏的情况少出现
	 * 优化合并时候将分组数量少的合并到数量多的分组中这样查询的树高度不变，利于快速查询
	 * @param q
	 * @param p
	 */
	public void union(int q, int p) {
		if(connected(q, p))
			return;
		//这个地方不需要循环给每一个分组中的节点修改父节点了
		//只要将一个分组的根节点改为另一个就实现了两个分组中的所有元素合组
		eleAndGroup[p] = find(q);
		N--;
	}
}
