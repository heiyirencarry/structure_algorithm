
package com.black.boy.structure.tree.union;

/**
 * 数组实现的并查集  数组中保存的是元素所在组的标识
 * @author eBuy
 *
 */
public class UnionSet {
	
	//分组的数量
	private int N;
	//记录节点元素和该元素对应的分组
	private int[] eleAndGroup;
	
	public UnionSet(int count) {
		N = count;
		eleAndGroup = new int[N];
		for(int i=0; i<N; ++i)
			eleAndGroup[i] = i;//每一个元素初始化一个分组为所在的索引位置
	}
	
	/**
	 * 获得当前并查集的分组数量
	 */
	public int getGroupCount() {
		return N;
	}
	
	/**
	 * 判断p和q元素是否在同一个分组
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean connected(int p, int q) {
		return eleAndGroup[p] == eleAndGroup[q];
	}
	
	/**
	 * 查找q元素所在分组的标识
	 * @param q
	 * @return
	 */
	public int find(int q) {
		return eleAndGroup[q];
	}
	
	/**
	 * 合并q和p为一个分组
	 * @param q
	 * @param p
	 */
	public void union(int q, int p) {
		if(connected(p, q))
			return;
		int _q = find(q);
		int _p = find(p);
		for (int i=0,len=eleAndGroup.length; i<len; ++i) {
			if(eleAndGroup[i] == _p)//将是p分组的所有元素到合并到q分组中
				eleAndGroup[i] = _q;
		}
		N--;
	}
}