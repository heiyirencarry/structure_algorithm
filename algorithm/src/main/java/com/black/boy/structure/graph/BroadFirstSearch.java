package com.black.boy.structure.graph;

import com.black.boy.structure.Queue;

public class BroadFirstSearch {

	private boolean[] marked; //顶点是否搜索的数组
	private int count; //记录多少个顶点与s相通
	private Queue<Integer> linked; //与顶点s相邻的所有顶点
	
	/**
	 * 广度搜索领接表的图顶点是s
	 * @param g
	 * @param s
	 */
	public BroadFirstSearch(GraphLinked g, int s) {
		count = 0;
		marked = new boolean[g.nodeCount()];
		linked = new Queue<Integer>();
		bfs(g, 0);
	}
	
	private void bfs(GraphLinked g, int s) {
		// s 是顶点标记为已查询并入队列
		mark(s);
		linked.entryQueue(s);
		//遍历队列中的顶点
		while(!linked.isEmpty()) {
			Integer q = linked.quitQueue();
			Queue<Integer> adj = g.getAdj(q);//获取到领接表
			for (Integer n : adj) {
				if(!isMark(n))
					bfs(g, n);
			}
		}
		count++;
	}
	
	/**
	 * 判断顶点n是否搜索过了
	 * @param n
	 * @return
	 */
	public boolean isMark(int n) {
		return marked[n];
	}
	
	private void mark(int n) {
		marked[n] = true;
	}
	
	/**
	 * 和顶点s相通的顶点个数
	 * @return
	 */
	public int count() {
		return count;
	}
}
