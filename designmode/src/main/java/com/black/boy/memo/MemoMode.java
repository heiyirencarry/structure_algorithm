package com.black.boy.memo;

/**
 * 备忘录模式的缺点：角色状态需要完整存储到备忘录对象中，如果状态数据太多，那么资源上的消耗较大，非常耗内存
 * @author eBuy
 * 备忘录模式一共有三个类分别是：1、需要被保存状态的发起者；2、保存发起者状态的备忘录类；3、备忘录的管理类(如果多个状态需要保存管理类中集合处理)
 *如果需要保存发起者的全部属性可以封装属性和备忘录对象公用一个类使用clone的方式实现，如果需要保存的属性不是全量的新建一个单独的备忘录类
 *备忘录有两个接口，管理者只能看到窄接口，他只能将备忘录传递给其它对象，发起者能够看到的是宽接口，所有的属性值都能看到要恢复状态值
 */
public class MemoMode {
	public static void main(String[] args) {
		Originator init = new Originator();
		init.setStatus("init");
		ManagerMemo mng = new ManagerMemo();
		mng.setM(init.createMemoTo());
		init.setStatus("haha");
		init.show();
		init.setMemo(mng.getM());
		init.show();
	}
}

//发起人
class Originator {
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	} 
	public Memo createMemoTo() { //创建备忘录对象
		Memo m = new Memo();
		m.setStatus(this.status);
		return m;
	}
	public void setMemo(Memo m) { //恢复到备忘录对象的状态
		this.status = m.getStatus();
	}
	public void show() {
		System.out.println("status = " + this.status);
	}
}
//备忘录类
class Memo {
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
//备忘录对象的管理类
class ManagerMemo {
	private Memo m; //保存多个状态可以使用集合 map
	public Memo getM() {
		return m;
	}
	public void setM(Memo m) {
		this.m = m;
	}
}