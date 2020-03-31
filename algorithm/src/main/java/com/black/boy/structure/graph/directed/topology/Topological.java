package com.black.boy.structure.graph.directed.topology;

import com.black.boy.structure.Stack;
import com.black.boy.structure.graph.directed.Digraph;

/**
 * 拓扑排序的实现
 * @author eBuy
 *
 */
public class Topological {

	private Stack<Integer> stack; //保存排序好的线性顶点
	
	public Topological(Digraph g) {
		if(!hasCycle(g)) {
			stack = new DepthFirstOrder(g).reversPost();
		}
	}
	
	/**
	 * 判断有向图中是否存在环
	 * @param g
	 * @return
	 */
	private boolean hasCycle(Digraph g) {
		return new DirectedCycle(g).hasCycle();
//		return stack == null;
	}
	
	/**
	 * 获取拓扑排序的线性 栈
	 * @return
	 */
	public Stack<Integer> order() {
		return stack;
	}
}
