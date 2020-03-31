package com.black.boy.combination;

import java.util.ArrayList;
import java.util.List;

import com.black.boy.util.StringUtil;

/**
 * 什么时候使用组合模式呢：
 * 体现部分和整体层次的结构时，以及你希望用户可以忽略组合对象与单个对象的不同，统一使用组合结构中的所有对象时，就应该考虑组合模式了
 * 
 * 组合模式定义包含【人力资源、财务部门这些基本对象】和【分公司、事业部】等组合类层次的结构，基本对象可以被组合成更复杂的组合对象
 *而这个组合对象还可以被组合，这样不断的递归下去，在客户端任何使用到基本对象的地方都可以使用组合对象了
 *
 *用户不用关心到底是处理一个叶子节点还是组合节点，也不用为定义组合而写一些选择判断的语句。简单说就是客户端可以以一致的方式调用组合对象和基本对象
 * @author eBuy
 *
 */
public class Company {

	public static void main(String[] args) {
		BCompany root = new BCompany("ebuy");
		root.add( new HrCompany("ebuy人力资源"));
		root.add(new FinanceCompany("ebuy财务"));
		
		BCompany zb = new BCompany("北京分公司");
		zb.add(new HrCompany("北京人力资源"));
		zb.add(new FinanceCompany("北京财务"));
		root.add(zb);
		
		BCompany zs = new BCompany("深圳分公司");
		zs.add(new HrCompany("深圳人力资源"));
		zs.add(new FinanceCompany("深圳财务"));
		root.add(zs);
		root.show(0);
		System.out.println("*********************************");
		root.execute();
	}
}

//公司树状结构显示职能部门的接口
interface ICompany {
	void add(ICompany c);
	void remove(ICompany c);
	void show(int depth);
	void execute();
}

//这个是总公司的定义
class BCompany implements ICompany {
	protected String name;
	public BCompany(String name) {
		this.name = name;
	}
	List<ICompany> list = new ArrayList<ICompany>();
	@Override
	public void add(ICompany c) {
		list.add(c);
	}
	@Override
	public void remove(ICompany c) {
		list.remove(c);
	}
	@Override
	public void show(int depth) {
		System.out.println(StringUtil.stringRide("_ ",depth) + this.name);
		for (ICompany c : list) {
			c.show(depth + 2);
		}
	}
	@Override
	public void execute() {
		for (ICompany c : list) {
			c.execute();
		}
	}
}

//公司的分部门
class HrCompany extends BCompany {
	public HrCompany(String name) {
		super(name);
	}

	public void execute() {
		System.out.println(this.name + "人力资源部干活了");
	}
}
class FinanceCompany extends BCompany {
	public FinanceCompany(String name) {
		super(name);
	}

	public void execute() {
		System.out.println(this.name + "财务部门干活了");
	}
}