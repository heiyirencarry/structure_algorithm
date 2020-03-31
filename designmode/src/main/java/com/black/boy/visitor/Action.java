package com.black.boy.visitor;

/**
 * @author black boy
 *
 */
public abstract class Action { // 状态类， 接受person

	public abstract void getManConclusion(Person man);

	public abstract void getWomanConclusion(Person wMan);
}

class Success extends Action { // 状态的实现类， 成功， 多个的
	@Override
	public void getManConclusion(Person man) {
		System.out.println(man.getClass().getSimpleName() + "成功");
	}

	@Override
	public void getWomanConclusion(Person wMan) {
		System.out.println(wMan.getClass().getSimpleName() + "成功");
	}
}

abstract class Person {
	public abstract void accept(Action action); // 接受状态
}

class Man extends Person {
	@Override
	public void accept(Action action) {
		action.getManConclusion(this);
	}
}

class Woman extends Person {
	@Override
	public void accept(Action action) {
		action.getWomanConclusion(this);
	}
}