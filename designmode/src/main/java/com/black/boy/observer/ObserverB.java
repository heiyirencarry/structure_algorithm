package com.black.boy.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author eBuy
 *
 */
public class ObserverB implements Observer {

	@Override
	public void update(Observable o, Object arg) {
//		ObservableMsg msg = (ObservableMsg) o;
//		String name = msg.getName();
//		System.out.println("B update this name" + name);
		System.out.println("变化了：" + arg);
	}

}
