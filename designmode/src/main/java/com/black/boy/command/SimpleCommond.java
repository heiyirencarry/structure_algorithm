package com.black.boy.command;

/**
 * 行为请求者和行为实现者的紧耦合
 * @author eBuy
 *
 */
public class SimpleCommond {
	public static void main(String[] args) {
		Cooker cooker = new Cooker();
		
		cooker.chicken();
		cooker.steak();
	}
}

class Cooker {
	public void chicken() {
		System.out.println("炸了一个鸡排");
	}
	public void steak() {
		System.out.println("做了一个牛排");
	}
}