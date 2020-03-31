package com.black.boy.structure.graph.directed;

import com.black.boy.structure.Queue;

/**
 * 有向图的API
 * @author eBuy
 *
 */
public class Digraph {

	//图中顶点个数
	private int V; 
	//图中边的个数
	private int E;
	//顶点的领接表
	private Queue<Integer>[] adj;
	
	public Digraph(int v) {
		V = v;
		E = 0;
		adj = new Queue[V];
		for(int i=0; i<V; i++) {
			adj[i] = new Queue<Integer>();
		}
	}
	
	/**
	 * 顶点的个数
	 * @return
	 */
	public int nodeSize() {
		return V;
	}
	
	/**
	 * 边的条数
	 * @return
	 */
	public int sideSize() {
		return E;
	}
	
	/**
	 * 有向图中添加边，v是起点，n是终点
	 * @param v
	 * @param n
	 */
	public void addSide(int v, int n) {
		adj[v].entryQueue(n);
		E++;
	}
	
	/**
	 * 获取某一个顶点的领接表
	 * @param v
	 * @return
	 */
	public Queue<Integer> getAdj(int v) {
		return adj[v];
	}
	
	/**
	 * 获取图的方向图
	 * @return
	 */
	public Digraph reverse() {
		Digraph r = new Digraph(V);//反向 图对象
		
		//循环每个顶点的领接表，然后以领接表中的元素为起点，以当前顶点为终点添加到反向图边中
		for(int i=0; i<V; i++) { 
			for (Integer n : adj[i]) {
				r.addSide(n, i);
			}
		}
		
		return r;
	}
}
