package com.black.boy.structure.graph.kruskal;

import com.black.boy.structure.Queue;
import com.black.boy.structure.graph.weight.Edge;
import com.black.boy.structure.graph.weight.WeightGraph;
import com.black.boy.structure.heap.priority.MinPriorityHeap;
import com.black.boy.structure.tree.union.TreeUnionSetSize;

public class KruskalMST {

	private Queue<Edge> mst; //保存最小生成树的边
	private TreeUnionSetSize uf; //索引代表顶点，使用uf.connect(v,w)可以判断顶点v和w是否属于同一棵树中
								 //使用uf.union(v,w) 可以把顶点v和顶点w的树合并
	private MinPriorityHeap<Edge> minQueue; //使用最小优先队列，对边按照权重排序
	
	public KruskalMST(WeightGraph g) {
		
		minQueue = new MinPriorityHeap<Edge>(g.nodeSize() + 1);
		mst = new Queue<Edge>();
		uf = new TreeUnionSetSize(g.nodeSize());
		
		Queue<Edge> edges = g.allEdge();
		for (Edge edge : edges) {
			minQueue.add(edge);
		}
		
		while(!minQueue.isEmpty() && mst.size() < g.nodeSize() -1) {
			Edge edge = minQueue.delMin();
			int v = edge.either();
			int w = edge.other(v);
			if(!uf.connected(v, w)) {
				uf.union(v, w);
				mst.entryQueue(edge);
			}
		}
	}
	
	public Queue<Edge> edges(){
		return mst;
	}
}
