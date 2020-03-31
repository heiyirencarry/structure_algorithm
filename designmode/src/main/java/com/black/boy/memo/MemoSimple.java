package com.black.boy.memo;

/**
 * 简单实现一个游戏进度条的保存功能，可以恢复到保存的状态
 * @author eBuy
 *客户端来设置恢复的状态，而且我们要求保存的状态是多个，这样就难以满足需求了，这个时候需要备忘录模式
 */
public class MemoSimple {

	public static void main(String[] args) {
		GameRole gameRole = new GameRole();
		gameRole.init();
		System.out.println("打架前：" + gameRole.toString());
		gameRole.execute();
		System.out.println("打架后：" + gameRole.toString());
		gameRole.setBlood(100);
		gameRole.setFight(100);
		System.out.println("恢复后：" + gameRole.toString());
	}
}

//游戏的角色
class GameRole {
	private int blood;
	private int fight;
	public int getBlood() {
		return blood;
	}
	public void setBlood(int blood) {
		this.blood = blood;
	}
	public int getFight() {
		return fight;
	}
	public void setFight(int fight) {
		this.fight = fight;
	}
	
	@Override
	public String toString() {
		return "GameRole [blood=" + blood + ", fight=" + fight + "]";
	}
	public void init() {
		this.blood = 100;
		this.fight = 100;
	}
	public void execute() {
		this.blood = 0;
		this.fight = 0;
	}
}