package com.black.boy.memo;

import java.util.HashMap;
import java.util.Map;

/**
 * 备忘录模式实现游戏进度条的保存功能
 * @author eBuy
 *
 */
public class MemoGame {

	public static void main(String[] args) {
		
		GMRole role = new GMRole(); // 创建一个游戏角色
		role.init(100, 100,100); //初始化一个状态
		role.show();
		
		RoleStatusMng mng = new RoleStatusMng(); // 这个对象时管理备份类的  可以认为备份的类就是一个缓存，需要的时候拿出来恢复一下
		mng.saveRoleStatus("init", role.saveStatus());//保存初始化的状态
		
		for(int i = 0; i< 5; i++) {
			role.fight();
			if(i == 3) { //当游戏角色打到3次后再保存一下当前的状态
				mng.saveRoleStatus("3",role.saveStatus());
			}
		}
		
		role.recoveryStatus(mng.getRoleStatusBykey("3"));
		System.out.println(role);
		role.recoveryStatus(mng.getRoleStatusBykey("init"));
		System.out.println(role);
		role.recoveryStatus(mng.getRoleStatusBykey("3"));
		System.out.println(role);
		
	}
}

class GMRole {
	// 游戏角色的 生命值、战斗力、防御能力的基本属性
	private int blood;
	private int attack;
	private int defense;
	
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
		return "GMRole [blood=" + blood + ", attack=" + attack + ", defense=" + defense + "]";
	}
	// 显示游戏角色的状态
	public void show() {
		System.out.println(toString());
	}
	//保存当前游戏角色的状态
	public RoleStatusMemento saveStatus() {
		return new RoleStatusMemento(this.blood, this.attack, this.blood);
	}
	public void recoveryStatus(RoleStatusMemento r) {
		if(r == null)
			throw new RuntimeException("recoveryStatus RoleStatusMemento not be null");
		this.blood = r.getBlood();
		this.attack = r.getAttack();
		this.defense = r.getAttack();
	}
	
	public void init(int b, int a, int d) {
		this.blood = b;
		this.attack = a;
		this.defense = d;
	}
	
	public void fight() {
		this.blood -= 1;
		this.attack -= 2;
		this.defense -= 3;
	}
}

// 角色状态的存储箱
class RoleStatusMemento {
	private int blood;
	private int attack;
	private int defense;
	
	public RoleStatusMemento(int b, int a, int d) {
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
}

// 角色状态的管理者
class RoleStatusMng {
	Map<String, RoleStatusMemento> map = new HashMap<String, RoleStatusMemento>();
	
	//获取角色的某一个状态
	public RoleStatusMemento getRoleStatusBykey(String key) {
		return map.get(key);
	}
	//保存角色的某一个状态
	public void saveRoleStatus(String key, RoleStatusMemento r) {
		map.put(key, r);
	}
}