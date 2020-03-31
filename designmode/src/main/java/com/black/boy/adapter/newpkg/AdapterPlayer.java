package com.black.boy.adapter.newpkg;

public class AdapterPlayer {
	public static void main(String[] args) {
		Player yaoming = new AdpCHCenterForward("姚明");
		yaoming.attack();
		yaoming.defense();
		Player liyi = new Guard("李毅");
		liyi.attack();
		liyi.defense();
	}
}
abstract class Player { //运动员的定义的一个抽象类
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Player(String name) {
		this.name = name;
	}
	public Player() {
	}
	public abstract void attack();//进攻
	public abstract void defense();//防守
}
class Guard extends Player {
	public Guard(String name) {
		super(name);
	}
	@Override
	public void attack() {
		System.out.println(this.getName() + "后卫进攻");
	}
	@Override
	public void defense() {
		System.out.println(this.getName() + "后卫防守");
	}
}
class CHCenterForward extends Player {
	public CHCenterForward(String name) {
		super(name);
	}
	@Override
	public void attack() {
		System.out.println(this.getName() + "中国的中锋进攻");
	}
	@Override
	public void defense() {
		System.out.println(this.getName() + "中国的中锋防守");
	}
}
class AdpCHCenterForward extends Player {
	public AdpCHCenterForward(String name) {
		super(name);
	}
	private CHCenterForward chd = new CHCenterForward(this.getName());
	@Override
	public void attack() {
		this.chd.attack();
	}
	@Override
	public void defense() {
		this.chd.defense();
	}
}