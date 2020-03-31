package com.black.boy.structure.graph.directed.topology;

import com.black.boy.structure.Queue;
import com.black.boy.structure.graph.directed.Digraph;

/**
 * 有向图中是否存在环的检验
 * 深度搜索中每一次深度搜索的路径上是否有搜索过的顶点，通过onStack数组记录每一次递归
 * 深度搜索的顶点，结束一次深度搜索的时候再将onStack中保存的值置为false
 * @author eBuy
 *
 */
public class DirectedCycle {

	private boolean[] marked;//顶点是否被搜索
	private boolean[] onStack;//每一次深度搜索顶点被搜索的标识 ,使用栈的思想记录顶点是否再当前搜索的路径上
	private boolean hasCycle; //有没有环的标识
	
	public DirectedCycle(Digraph g) {
		marked = new boolean[g.nodeSize()];
		onStack = new boolean[g.nodeSize()];
		hasCycle = false;
		for(int i=0; i<g.nodeSize(); i++) {
			if(!marked[i])
				dfs(g, i);
		}
	}
	
	/**
	 * 图的深度搜索，检测有向图中是否存在环
	 * @param g
	 * @param v
	 */
	private void dfs(Digraph g, int v) {
		//标记顶点为搜索状态
		marked[v] = true;
		//onStack 改变标识
		onStack[v] = true;
		//获取顶点v的连接表队列
		Queue<Integer> q = g.getAdj(v);
		//遍历链接表中的每一个顶点递归搜索
		for (Integer n : q) {
			
			//判断当前顶点没有被搜索过，递归继续深度搜索
			if(!marked[n]) {
				dfs(g, n);
			}
			
			//判断当前顶点n是否已经在栈中，如果在就代表n顶点正在搜索状态中，那么再次搜索到证明换得存在
			if(onStack[n]) {
				hasCycle = true;
				return;
			}
		}
		//深度搜索结束或者检测到有环的存在，出栈，就是onStack数组的标识变为初始状态
		onStack[v] = false;
	}
	
	/**
	 * 有向图中是否存在环
	 * @return
	 */
	public boolean hasCycle() {
		return hasCycle;
	}
}
