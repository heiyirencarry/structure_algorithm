package com.black.boy.visitor.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author black boy
 *
 */
public class Client {
	public static void main(String[] args) {
		ObjectAction o = new ObjectAction();
		o.addPerson(new Man("N1"));
		o.addPerson(new Man("N2"));
		o.addPerson(new Woman("V1"));
		o.addPerson(new Woman("V2"));
		Action a = new Success();
		a = new Failed();
		o.execute(a);
	}
}

abstract class Action {
	public abstract void getManConclusion(Man man);

	public abstract void getWomanConclusion(Woman wman);
}

class Success extends Action {

	@Override
	public void getManConclusion(Man man) {
		System.out.println(man.getName() + " " + this.getClass().getSimpleName());
	}

	@Override
	public void getWomanConclusion(Woman wman) {
		System.out.println(wman.getName() + " " + this.getClass().getSimpleName());
	}
}

class Failed extends Action {

	@Override
	public void getManConclusion(Man man) {
		System.out.println(man.getName() + " " + this.getClass().getSimpleName());
	}

	@Override
	public void getWomanConclusion(Woman wman) {
		System.out.println(wman.getName() + " " + this.getClass().getSimpleName());
	}
}

abstract class Person {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract void accept(Action action);
}

class Man extends Person {
	public Man(String name) {
		this.setName(name);
	}

	@Override
	public void accept(Action action) {
		action.getManConclusion(this);
	}
}

class Woman extends Person {
	public Woman(String name) {
		this.setName(name);
	}

	@Override
	public void accept(Action action) {
		action.getWomanConclusion(this);
	}
}

class ObjectAction {
	List<Person> list = new ArrayList<Person>();

	public void addPerson(Person p) {
		list.add(p);
	}

	public void execute(Action a) {
		for (Person p : list) {
			p.accept(a);
		}
	}
}
