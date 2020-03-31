package com.black.boy.bridge;

/**
 * 一个系统多个角度考虑都会引起变化，如果以继承的方式实现比较臃肿且不易扩展
 * @author eBuy
 *
 */
public class BridgeSimple {
	public static void main(String[] args) {
		BrandPhone ph = new GameN();
		ph.run();
		ph = new AddressM();
		ph.run();
	}
}

/**
 * 手机品牌的抽象
 * @author eBuy
 * 如果两个维度的变化继承自一个体系中那么，一旦新增一个品牌或者新增一个软件功能，维护量是相当的大
 * 这个时候继承就不是很合适，使用组合的方式，(合成：强引用 大雁和大雁的翅膀，聚合：弱引用 大雁和雁群)
 */
class BrandPhone {
	public void run() {}
}
//手机的品牌会变化  一个维度的变化
class N extends BrandPhone {
}

class M extends BrandPhone{
}
//手机中软件功能会变化 另外一个维度的变化
class GameN extends N {
	public void run() {System.out.println("N品牌的手机运行游戏");}
}
class GameM extends M {
	public void run() {System.out.println("M品牌的手机运行游戏");}
}
class AddressN extends N {
	public void run() {System.out.println("N品牌的通信录运行");}
}
class AddressM extends M {
	public void run() {System.out.println("M品牌的通信录运行");}
}
