package com.black.boy.memo;

import java.util.HashMap;
import java.util.Map;

/**
 * 这里将备忘录对象(说白了就是保存角色状态属性的对象就是备忘录对象)和角色的属性对象封装到一个param类中了
 * 公用的是一个类，使用对象的克隆来处理不同状态的保存，如果只保存角色对象属性的部分值可以单独声明备忘录对象保存需要保存的属性，这样也可以节省内存的消耗
 * @author eBuy
 *
 */
public class MemoSuper {

	public static void main(String[] args) {
		Param p = new Param(100, 100, 100); //初始化的角色状态
		Role r = new Role(p);
//		r.show();//初始化状态
		
		ManagerStatus mng = new ManagerStatus();
//		mng.addStatus(p, "init"); //保存初始化的状态 这个是错误的做法，p指向同一个对象
		mng.addStatus(r.saveStat(), "init"); //保存初始化的状态 克隆出来的对象相互独立
		
		for(int i = 0; i< 5; i++) {
			r.execute(); //一共消耗5次
			if(i == 3) 
				mng.addStatus(r.saveStat(), "3"); //保打了三次时的状态
		}
		r.show();
		r.recoverStat(mng.getStatus("init")); //恢复到初始化的状态
		r.show();
		r.recoverStat(mng.getStatus("3")); //恢复到第三次的状态
		r.show();
		System.out.println("保存的状态之间可以随意的恢复，随心所欲");
	}
}

//游戏角色的类
class Role {
	private Param param;
	public Role(Param p) {
		this.param = p;
	}
	
	// 保存状态的方法
	public Param saveStat() {
		try {
			return this.param.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//恢复状态的方法
	public void recoverStat(Param p) {
		this.param = p;
	}
	
	public void execute() { //角色每一次就消耗所有的属性2个点
		this.param.setBlood(this.param.getBlood() -2);
		this.param.setAttack(this.param.getAttack() -2);
		this.param.setDefense(this.param.getDefense() -2);
	}
	
	//显示当前状态的方法
	public void show() {
		System.out.println(this.param.toString());
	}
}

//角色状态管理者
class ManagerStatus {
	private Map<String, Param> map = new HashMap<String, Param>();
	
	public void addStatus(Param p, String key) {
		map.put(key, p);
	}
	
	public Param getStatus(String key) {
		return map.get(key);
	}
}

//封装游戏角色的属性对象 类似pojo
class Param implements Cloneable {
	private int blood;
	private int attack;
	private int defense;
	public Param() {}
	public Param (int b, int a, int d) {
		this.blood = b;
		this.attack = a;
		this.defense = d;
	}
	public int getBlood() {
		return blood;
	}
	public void setBlood(int blood) {
		this.blood = blood;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	@Override
	public String toString() {
		return "Param [blood=" + blood + ", attack=" + attack + ", defense=" + defense + "]";
	}
	
	public Param clone() throws CloneNotSupportedException {
		return (Param)super.clone();
	}
}