package com.black.boy.structure.graph.prim;

import com.black.boy.structure.Queue;
import com.black.boy.structure.graph.weight.Edge;
import com.black.boy.structure.graph.weight.WeightGraph;
import com.black.boy.structure.heap.priority.IndexPriorityMinHeap;

public class PrimMST {

	private Edge[] edgeTo; //索引代表顶点，值表示当前顶和最小生成树之间的最短路径
	private Double[] distTo;//索引代表顶点，值表示当前顶点和最小生成树之间的最短边的权重
	private boolean[] marked; //索引代表顶点，如果当前顶点存在树中，则值为true，否则为false
	private IndexPriorityMinHeap<Double> pq; //存放树中顶点与非树顶点的横切边
	
	public PrimMST(WeightGraph g) {
		marked = new boolean[g.nodeSize()];
		edgeTo = new Edge[g.nodeSize()];
		
		//因为保存的是权重值，所以初始化为最大的double值，然后比较保存较小者
		distTo = new Double[g.nodeSize()];
		for(int i=0; i<g.nodeSize(); ++i) {
			distTo[i] = Double.MAX_VALUE;
		}
		
		pq = new IndexPriorityMinHeap<Double>(g.nodeSize());
		
		distTo[0] = 0.0;
		pq.add(0, 0.0);
		while (!pq.isEmpty()) {
			visit(g, pq.delIndexMin());
		}
	}
	
	/**
	 * 将顶点v添加到最小生成树中，并更新数据
	 * @param g
	 * @param v
	 */
	private void visit(WeightGraph g, int v) {
		//标记顶点v为已搜索
		marked[v] = true;
		//更新数据
		for(Edge e: g.getAdj(v)) {
			
			//获取另一个顶点
			int other = e.other(v);
			//判断另一个顶点是否存在树中，如果存在则不作任何处理
			if(marked[other])
				continue;
			//判断顶点e的权重是否小于other,从other顶点到树中获取权重最小的边的权重
			if(e.weight() < distTo[other]) {
				edgeTo[other] = e;
				distTo[other] = e.weight();
				
				if(pq.contains(other)) {
					pq.exchItem(other, e.weight());
				}else {
					pq.add(other, e.weight());
				}
			}
			
		}
	}
	
	/**
	 * 获取最小生成树的所有边
	 * @return
	 */
	public Queue<Edge> edges(){
		Queue<Edge> q = new Queue<Edge>();
		for (Edge edge : edgeTo) {
			if(edge != null)
				q.entryQueue(edge);
		}
		return q;
	}
	
}
