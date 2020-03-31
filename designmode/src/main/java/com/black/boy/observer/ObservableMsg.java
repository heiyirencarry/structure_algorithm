package com.black.boy.observer;

import java.util.Observable;

import com.black.boy.observer.msg.MsgA;
import com.black.boy.observer.msg.MsgB;

/**
 * @author eBuy
 *
 */
public class ObservableMsg extends Observable {

	private String name;
	private MsgA a;
	private MsgB b;

	public ObservableMsg(String name) {
		super();
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
		System.out.println("name changed: " + name);
		changed(name);
	}

	public void ChengA(MsgA a) {
		this.a = a;
		changed(a);
	}

	public void chengB(MsgB b) {
		this.b = b;
		changed(b);
	}

	private void changed(Object o) {
		setChanged();
		notifyObservers(o);
	}

	public String getName() {
		return this.name;
	}

	/**
	 * @return the a
	 */
	public MsgA getA() {
		return a;
	}

	/**
	 * @param a the a to set
	 */
	public void setA(MsgA a) {
		this.a = a;
	}

	/**
	 * @return the b
	 */
	public MsgB getB() {
		return b;
	}

	/**
	 * @param b the b to set
	 */
	public void setB(MsgB b) {
		this.b = b;
	}

}
