package com.black.boy.structure.graph.weight;

import com.black.boy.structure.Queue;

public class WeightGraph {
	
	//顶点数量
	private int V;
	//边的数量
	private int E;
	//领接表队列数组
	private Queue<Edge>[] adj;
	
	public WeightGraph(int n) {
		this.V = n;
		this.E = 0;
		this.adj = new Queue[V];
		for(int i=0; i<V; i++)
			adj[i] = new Queue<Edge>();
	}
	
	/**
	 * 获取图中的顶点数量
	 * @return
	 */
	public int nodeSize() {
		return V;
	}
	
	/**
	 * 获取边的条数
	 * @return
	 */
	public int edgeSize() {
		return E;
	}
	
	/**
	 * 加权无向图边的添加
	 * @param e
	 */
	public void addEdge(Edge e) {
		int m = e.either();
		adj[m].entryQueue(e);
		int n = e.other(m);
		adj[n].entryQueue(e);
		E++;
	}
	
	/**
	 * 获取某一个顶点的领接表(边)
	 * @param n
	 * @return
	 */
	public Queue<Edge> getAdj(int n){
		return adj[n];
	}
	
	/**
	 * 获取加权无向图的所有边
	 * @return
	 */
	public Queue<Edge> allEdge(){
		Queue<Edge> all = new Queue<Edge>();
		for(int i=0; i<adj.length; i++) {
			//获取i顶点的领接表，遍历
			for (Edge e : adj[i]) {
				//因为是i的领接表说明i肯定是边的一个顶点，获取另一个顶点
				int n = e.other(i);
				//因为在无向图中一跳边会出现在该边两个领接表中，所以我们只取边顶点中大的领接表中的边，这样就去掉重复的了
				if(i > n) {
					all.entryQueue(e);
				}
			}
		}
		return all;
	}
}