package com.black.boy.structure.graph;

import com.black.boy.structure.Queue;

/**
 * 图的深度优先搜索
 * @author eBuy
 *
 */
public class DepthFirstSearch {

	private int count; //记录有多少个顶点与s顶点相通
	private boolean[] marked; //索引代表顶点，值代表当前顶点是否被搜索过
	
	/**
	 * 构造深度优先搜索的对象，找到和顶点n相通的所有顶点
	 * @param g 图
	 * @param n 顶点
	 */
	public DepthFirstSearch(GraphLinked g, int n) {
		count = 0;
		marked = new boolean[g.nodeCount()];
		dfs(g, n);
	}
	
	/**
	 * 深度遍历顶点n的所有领接表
	 * @param g
	 * @param n
	 */
	private void dfs(GraphLinked g, int n) {
		marked[n] = true;
		Queue<Integer> q = g.getAdj(n); //获取节点n的邻接表
		for (Integer c : q) { //遍历n节点的领接表，没有被搜索的递归调用搜索
			if(!marked(c))
				dfs(g, c);
		}
		count++;
	}
	
	/**
	 * 判断顶点v和顶点n是否相通
	 * @param v
	 * @return
	 */
	public boolean marked(int v) {
		return marked[v];
	}
	
	/**
	 * 获取与顶点相邻的顶点数量
	 * @return
	 */
	public int count() {
		return count;
	}
	
}
