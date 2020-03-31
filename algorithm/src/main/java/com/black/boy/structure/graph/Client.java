package com.black.boy.structure.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.black.boy.structure.Stack;

public class Client {

	public static void main(String[] args) throws IOException {
		//深度优先搜索测试
//		GraphLinked g = new GraphLinked(13);
//		g.addSide(0, 1);
//		g.addSide(0, 2);
//		g.addSide(0, 6);
//		g.addSide(0, 5);
//		g.addSide(6, 4);
//		g.addSide(3, 4);
//		g.addSide(5, 4);
//		g.addSide(5, 3);
//		g.addSide(7, 8);
//		g.addSide(9, 10);
//		g.addSide(9, 11);
//		g.addSide(9, 12);
//		g.addSide(11, 12);
//		
//		BroadFirstSearch dfs = new BroadFirstSearch(g, 0);
//		System.out.println(dfs.isMark(9));
//		System.out.println(dfs.isMark(6));
//		System.out.println(dfs.count());
		pathTest();
	}
	
	private static void pathTest() throws IOException {
		InputStream in = Client.class.getClassLoader().getResourceAsStream("road.txt");
		BufferedReader rd = new BufferedReader(new InputStreamReader(in));
		
		GraphLinked g = new GraphLinked(toInt(rd.readLine()));//第一行是顶点数量
		Integer len = toInt(rd.readLine());//第二行是边数量
		for(int i=0; i< len; i++) {
			String line = rd.readLine();
			String[] s = line.split(" ");
			g.addSide(toInt(s[0]), toInt(s[1]));
		}
		
		DepthFirstPaths bs = new DepthFirstPaths(g, 0);
		System.out.println(bs.hasPathsTo(5));
		System.out.println(bs.hasPathsTo(4));
		Stack<Integer> stack = bs.getPath(1);
		
		while(!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
		
	}
	
	private static Integer toInt(String s) {
		return Integer.parseInt(s);
	}
}
