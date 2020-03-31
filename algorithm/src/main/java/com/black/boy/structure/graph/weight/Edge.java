package com.black.boy.structure.graph.weight;

/**
 * 加权无向图的边 对象
 * @author eBuy
 *
 */
public class Edge implements Comparable<Edge>{

	private int M;//边的一个顶点
	private int N;//边的一个顶点
	private Double val;//权重值
	
	public Edge(int m, int n, Double val) {
		M = m;
		N = n;
		this.val = val;
	}
	
	/**
	 * 获取边对象的权重值
	 * @return
	 */
	public Double weight() {
		return val;
	}
	
	/**
	 * 返回改边的任意一个顶点
	 * @return
	 */
	public int either() {
		return N;
	}
	
	/**
	 * 返回边的另一个顶点
	 * @param n 边的一个顶点
	 * @return
	 */
	public int other(int n) {
		return n == N ? M : N;
	}
	
	@Override
	public int compareTo(Edge o) {
//		double r = this.val - o.weight();
//		if(r > 0)
//			return 1;
//		else if(r < 0)
//			return -1;
//		else
//			return 0;
		return this.val.compareTo(o.weight());
	}
	
}
