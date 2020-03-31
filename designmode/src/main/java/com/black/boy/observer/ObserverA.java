package com.black.boy.observer;

import java.util.Observable;
import java.util.Observer;

import com.black.boy.observer.msg.MsgA;
import com.black.boy.observer.msg.MsgB;

/**
 * @author eBuy
 *
 */
public class ObserverA implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		ObservableMsg msg = (ObservableMsg) o;
		if (arg instanceof String) {
			System.out.println("改变的是name属性");
			System.out.println(msg.getName());
		} else if (arg instanceof MsgA) {
			System.out.println("改变的是A的属性");
			System.out.println(msg.getA());
		} else if (arg instanceof MsgB) {
			System.out.println("改变的是B的属性");
			System.out.println(msg.getB());
		}
	}

}
