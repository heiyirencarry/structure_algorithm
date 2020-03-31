package com.black.boy.command;

import java.util.ArrayList;
import java.util.List;

public class CookCommand {

	public static void main(String[] args) {
		CookerA ck = new CookerA("", "full");//一个厨师对象
//		CookerA ck = new CookerA("full", "full");//一个厨师对象
		Waiter wt = new Waiter();//一个服务员
		//开始点餐
		wt.add(new Chichen(ck));
		wt.add(new Steak(ck));
		Chichen cc = new Chichen(ck);
		wt.add(cc);
		wt.removeC(cc);
		//点餐结束了通知厨师干
		wt.notifyDoIt();
	}
}

//厨师类，只管自己能做什么菜，就是功能
class CookerA {
	//这两个状态应该是另外一个程序来修改，
	//如果是商城项目就是后管来修改库存，或者其它管理系统通知修改，
	//这里就在客户端修改模拟一下 提供一个构造方法和读写器方法修改
	private String chicken_status;
	private String steak_status;
	
	public CookerA(String c, String s) {
		this.chicken_status = c;
		this.steak_status = s;
	}
	public String getChicken_status() {
		return chicken_status;
	}
	public void setChicken_status(String chicken_status) {
		this.chicken_status = chicken_status;
	}
	public String getSteak_status() {
		return steak_status;
	}
	public void setSteak_status(String steak_status) {
		this.steak_status = steak_status;
	}
	
	public void chicken() {
		System.out.println("炸了一个鸡排");
	}
	public void steak() {
		System.out.println("做了一个牛排");
	}
}

/**
 * 命令类的抽象，抽象的就是厨师有的每一个功能都给抽成一个命令，也就是一个命令对应这执行者的一个功能
 * @author eBuy
 *
 */
abstract class ICommand{
	protected CookerA c;
	public ICommand(CookerA c) {
		this.c = c;
	}
	
	protected abstract String getCommandStatus();
	protected abstract void executeCommand();
}

/**
 * 做鸡排的命令实现类
 * @author eBuy
 *
 */
class Chichen extends ICommand { 
	public Chichen(CookerA c) {
		super(c);
	}
	@Override
	public void executeCommand() {
		c.chicken();
	}
	@Override
	protected String getCommandStatus() {
		return c.getChicken_status();
	}
}
/**
 * 做牛排的命令实现类
 * @author eBuy
 *
 */
class Steak extends ICommand {
	public Steak(CookerA c) {
		super(c);
	}
	@Override
	public void executeCommand() {
		c.steak();
	}
	@Override
	protected String getCommandStatus() {
		return c.getSteak_status();
	}
}

/**
 * 服务员类，主要的作用就是传递命令，客户也就是命令的请求者，厨师也就是命令的执行者，服务员在中间起到一个解耦的作用
 * 所有的命令的请求、撤销等等的操作都是客户请求服务员，服务员有一个订单列表，交给厨师
 * @author eBuy
 *
 */
class Waiter {
	//真实的环境可以把这个封转到一个共享的存储,提供修改的接口
	//判断只要执行者还没有执行就可以修改
	List<ICommand> commandOrder = new ArrayList<ICommand>(); 
	public void add(ICommand c) {
		if(isEmpty(c.getCommandStatus())) {
			System.out.println(c.getClass().getSimpleName() + "买完了请选择其它的");
		} else {
			commandOrder.add(c);
			//添加点餐的时间
		}
	}
	
	public void removeC(ICommand c) {
		commandOrder.remove(c);
		//添加取消菜单的时间和操作日志
	}
	
	public void notifyDoIt() {
		for (ICommand c : commandOrder) {
			c.executeCommand();
		}
	}
	
	private boolean isEmpty(String status) {
		return status == null || status.equals("");
	}
}

