package com.black.boy.structure.graph.kruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.black.boy.structure.Queue;
import com.black.boy.structure.graph.weight.Edge;
import com.black.boy.structure.graph.weight.WeightGraph;

public class Client {
	
	public static void main(String[] args) throws IOException {
		InputStream in = Client.class.getClassLoader().getResourceAsStream("digroup_tree.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		//初始化加权有向图
		WeightGraph g = new WeightGraph(toInt(br.readLine()));
		Integer num = toInt(br.readLine());
		for(int i=0; i<num; ++i) {
			String[] lines = br.readLine().split(" ");
			g.addEdge(new Edge(toInt(lines[0]), toInt(lines[1]), toDouble(lines[2])));
		}
		
		KruskalMST primMST= new KruskalMST(g);
		Queue<Edge> edges = primMST.edges();
		for (Edge edge : edges) {
			int v = edge.either();
			int o = edge.other(v);
			Double w = edge.weight();
			System.out.println(v+" ---> " + o + ": " + w);
		}
	}
	
	private static Integer toInt(String s) {
		return Integer.parseInt(s);
	}
	private static Double toDouble(String s) {
		return Double.parseDouble(s);
	}
}
