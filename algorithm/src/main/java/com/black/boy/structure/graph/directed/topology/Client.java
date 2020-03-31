package com.black.boy.structure.graph.directed.topology;

import com.black.boy.structure.Stack;
import com.black.boy.structure.graph.directed.Digraph;

public class Client {
	public static void main(String[] args) {
		Digraph g = new Digraph(6);
		g.addSide(0, 3);
		g.addSide(0, 2);
		g.addSide(3, 4);
		g.addSide(4, 5);
		g.addSide(1, 3);
		g.addSide(2, 4);
		
		Stack<Integer> order = new Topological(g).order();
		
		while(!order.isEmpty()) {
			Integer n = order.pop();
			System.out.println(n);
		}
	}
}
