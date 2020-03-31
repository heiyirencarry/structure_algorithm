package com.black.boy.structure.graph;

import com.black.boy.structure.Queue;
import com.black.boy.structure.Stack;

public class DepthFirstPaths {

	private boolean[] marked; //索引代表顶点，值代表是否被搜索过
	private int s; //起点
	private int[] edgeTo;//索引代表顶点，值代表到达该顶点的上一个顶点
	
	public DepthFirstPaths(GraphLinked g, int s) {
		this.s = s;
		marked = new boolean[g.nodeCount()];
		edgeTo = new int[g.nodeCount()];
		for(int i=0; i<g.nodeCount(); i++)
			edgeTo[i] = -1;
		dfs(g, s);
	}
	
	/**
	 * 深度搜索顶点s到图中所有的可达路径
	 * @param g
	 */
	private void dfs(GraphLinked g, int v) {
		if(!isMark(v)) {
			marked[v] = true;
			Queue<Integer> q = g.getAdj(v);
			for (Integer n : q) {
				edgeTo[n] = v;
				dfs(g, n);
			}
		}
		
//		marked[v] = true;
//		Queue<Integer> q = g.getAdj(v);
//		for (Integer n : q) {
//			if(!isMark(n)) {
//				edgeTo[n] = v;
//				dfs(g, n);
//			}
//		}
	}
	
	/**
	 * 判断顶点是否被搜索过
	 * @param v
	 * @return
	 */
	private boolean isMark(int v) {
		return marked[v];
	}
	
	/**
	 * 判断是否存在订顶点到v顶点的路径存在
	 * @param v
	 * @return
	 */
	public boolean hasPathsTo(int v) {
//		return edgeTo[v] != -1;
		return marked[v];//查找过肯定是通的，就是有路径存在
	}
	
	/**
	 * 获取顶点到v顶点的一跳路径
	 * @param v
	 * @return
	 */
	public Stack<Integer> getPath(int v){
		Stack<Integer> stack = new Stack<Integer>();
		
		while(v != s) {
			stack.put(v);
			v = edgeTo[v];
		}
		
//		for(int x=v; x != s; x=edgeTo[x]) {
//			stack.put(x);
//		}
		stack.put(s);//在把顶点放入
		return stack;
	}
	
}
