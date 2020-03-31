package com.black.boy.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author black boy
 * 
 *         内部状态：可以共享，不会随环境的变化而变化
 * 
 *         外部状态：不可以共享，会随环境的变化而变化
 *
 *         极大减少内存对象的使用，
 * 
 *         相同或者相似对象内存中只有一份，极大的节约资源，
 * 
 *         提高系统性能 外部状态相对独立，不影响内部状态
 * 
 *         String类的设计也是享元模式，各种池：线程池、数据库池等也可以使用享元模式
 */
public class Piece {

	public static void main(String[] args) {
		IPiece b = FlyWeightFactory.getPiece("黑色");
		IPiece b1 = FlyWeightFactory.getPiece("黑色");
		IPiece r = FlyWeightFactory.getPiece("红色");
		IPiece r1 = FlyWeightFactory.getPiece("红色");
		b.display(new PieceOuter(1, 2));
		b1.display(new PieceOuter(2, 3));
		System.out.println(b == b1);
		r.display(new PieceOuter(4, 5));
		r1.display(new PieceOuter(6, 1));
		System.out.println(r == r1);
	}
}

class FlyWeightFactory {
	private static Map<String, IPiece> map = new HashMap<String, IPiece>();

	// 获取共享的对象
	public static IPiece getPiece(String color) {
		IPiece p = map.get(color);
		if (p == null) {
			p = new PieceColor(color);
			map.put(color, p);
		}
		return p;
	}
}

//棋子接口
interface IPiece {
	void setColor(String c);

	String getColor();

	void display(PieceOuter o);
}

class PieceColor implements IPiece {

	private String color;

	public PieceColor(String color) {
		this.color = color;
	}

	@Override
	public void setColor(String c) {
		this.color = c;
	}

	@Override
	public String getColor() {
		return this.color;
	}

	@Override
	public void display(PieceOuter o) {
		System.out.println(color + " " + o.toString());
	}

}

//棋子的外部属性
class PieceOuter {

	private int x;
	private int y;

	public PieceOuter(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "PieceOuter [x=" + x + ", y=" + y + "]";
	}
}