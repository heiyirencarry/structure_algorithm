package com.black.boy.structure.graph;

import com.black.boy.structure.Queue;

/**
 * 邻接表实现图的存储和操作
 * @author eBuy
 *
 */
public class GraphLinked {

	//图中节点的数量
	private final int nodeCount;
	//图中边的数量
	private int sideCount;
	//邻接表
	private Queue<Integer>[] adj;
	
	public GraphLinked(int nodeCount) {
		this.nodeCount = nodeCount;
		this.sideCount = 0;
		this.adj = new Queue[nodeCount];
		
		for(int i=0; i<nodeCount; i++)
			adj[i] = new Queue<Integer>();
	}
	
	/**
	 * 获取图中的顶点数量
	 * @return
	 */
	public int nodeCount() {
		return nodeCount;
	}
	
	/**
	 * 获取边的数量
	 * @return
	 */
	public int sideCount() {
		return sideCount;
	}
	
	/**
	 * 图中添加一条边 m和n是边的两个顶点
	 * @param m
	 * @param n
	 */
	public void addSide(int m, int n) {
		//无向图添加边，与m相邻的顶点队列中有n，与n相邻的顶点队列中有m
		adj[m].entryQueue(n);//m的邻接表队列中添加n
		adj[n].entryQueue(m);//n的邻接表队列中添加m
		
		//边的数量加一
		sideCount++;
	}
	
	/**
	 * 获取所有与顶点v相邻的顶点，返回队列
	 * @param v
	 * @return
	 */
	public Queue<Integer> getAdj(int v) {
		return adj[v];
	}
}
