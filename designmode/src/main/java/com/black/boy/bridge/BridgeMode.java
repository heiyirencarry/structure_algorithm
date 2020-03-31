package com.black.boy.bridge;

public class BridgeMode {
	public static void main(String[] args) {
		
		//如果新增软件新增一个类，如果新增品牌也是新增一个类
		
		//不同的软件
		PhoneSoftware1 game = new Game();
		PhoneSoftware1 adrs = new Address();
		
		//不同的品牌
		PhoneBrand1 ph = new XiaoMiPhone();
		ph.setSoftwares(adrs);
		ph.run();
		
		ph = new MeizuPhone();
		ph.setSoftwares(game);
		ph.run();
	}
}

//品牌的一个抽象
abstract class PhoneBrand1 {
	protected PhoneSoftware1 softwares;

	public void setSoftwares(PhoneSoftware1 softwares) {
		this.softwares = softwares;
	}
	
	public abstract void run();
}

class XiaoMiPhone extends PhoneBrand1 {
	public void run() {
		softwares.run();
		System.out.println("品牌" + this.getClass().getSimpleName());
	}
}
class MeizuPhone extends PhoneBrand1 {

	@Override
	public void run() {
		softwares.run();
		System.out.println("品牌" + this.getClass().getSimpleName());
	}
	
}

//软件的一个抽线
abstract class PhoneSoftware1 {
	public abstract void run();
}
class Game extends PhoneSoftware1 {
	public void run() {
		System.out.println("游戏运行");
	}
}
class Address extends PhoneSoftware1 {
	public void run() {
		System.out.println("通讯录运行");
	}
}