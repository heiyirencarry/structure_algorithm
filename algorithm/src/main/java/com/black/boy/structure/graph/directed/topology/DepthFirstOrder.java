package com.black.boy.structure.graph.directed.topology;

import com.black.boy.structure.Queue;
import com.black.boy.structure.Stack;
import com.black.boy.structure.graph.directed.Digraph;

/**
 * 顶点排序api
 * @author eBuy
 *
 */
public class DepthFirstOrder {
	
	private boolean[] marked; //索引代表顶点，值表示当前顶点是否被搜索
	private Stack<Integer> reversePost; //使用栈存储顶点序列
	
	public DepthFirstOrder(Digraph g) {
		marked = new boolean[g.nodeSize()];
		reversePost = new Stack<Integer>();
		
		//循环搜索每一个没有搜索到的顶点
		for(int i=0; i<g.nodeSize(); ++i) {
			if(!marked[i]) {
				dfs(g, i);
			}
		}
	}
	
	/**
	 * 深度优先搜索有向图
	 * @param g
	 * @param v
	 */
	public void dfs(Digraph g, int v) {
		//标记顶点v为已搜索状态
		marked[v] = true;
		Queue<Integer> q = g.getAdj(v);
		//遍历顶点v的领接表递归搜索
		for (Integer n : q) {
			if(!marked[n]) {
				dfs(g, n);
			}
		}
		//顶点v进入栈
		reversePost.put(v);
	}
	
	/**
	 * 获取顶点线性序列
	 * @return
	 */
	public Stack<Integer> reversPost() {
		return reversePost;
	}
}
