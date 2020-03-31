package com.black.boy.combination;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eBuy
 * 组合设计模式：将对象组合成树形结构以表示部分整体的层次结构,组合模式使得用户对单个对象和组合对对象的使用具有一致性
 *
 */
public class Combination {
	public static void main(String[] args) {
		Composite zg = new Composite("中国");
		Composite sx = new Composite("陕西");
		Composite bj = new Composite("宝鸡");
		Composite xa = new Composite("西安");
		LeafC l = new LeafC("liyi");
		LeafC h = new LeafC("haha");
		bj.add(l);
		bj.add(h);
		sx.add(bj);
		sx.add(xa);
		zg.add(sx);
		zg.show(1);
	}
}

//树可能有无数的分支，但是只需要反复的使用composite就可以实现树状结构
abstract class AbsCombination {
	public static final String SPLIT_CHAR = "_";
	protected String name;
	public AbsCombination(String name) {
		this.name = name;
	}
	public abstract void add(AbsCombination c);
	public abstract void remove(AbsCombination c);
	public abstract void show(int depth);
	protected String stringRepeat(String s, int count) {
		if(isNull(s)) {
			throw new IllegalArgumentException("s["+s+"] not be empty");
		}
		if(count < 0) {
			throw new IllegalArgumentException("count error["+count+"]");
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < count; i++) {
			sb.append(s).append(" ");
		}
		return sb.toString();
	}
	private boolean isNull(String s) {
		return s == null || "".equals(s);
	}
}

class LeafC extends AbsCombination {
	public LeafC(String name) {
		super(name);
	}

	@Override
	public void add(AbsCombination c) {
		System.out.println("已经是叶子节点了，不能再添加了");
	}

	@Override
	public void remove(AbsCombination c) {
		System.out.println("叶子节点，不支持移除的功能了");
	}

	@Override
	public void show(int depth) {
		System.out.println(stringRepeat(SPLIT_CHAR, depth) + name);
	}
}

class Composite extends AbsCombination {

	private List<AbsCombination> list = new ArrayList<AbsCombination>();
	public Composite(String name) {
		super(name);
	}

	@Override
	public void add(AbsCombination c) {
		list.add(c);
	}

	@Override
	public void remove(AbsCombination c) {
		list.remove(c);
	}

	@Override
	public void show(int depth) {
		System.out.println(stringRepeat(SPLIT_CHAR, depth) + name);
		for (AbsCombination a : list) {
			a.show(depth + 2);
		}
		
	}
	
}