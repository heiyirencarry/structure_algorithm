package com.black.boy.observer;

import com.black.boy.observer.msg.MsgA;

/**
 * @author eBuy
 *
 */
public class Client {

	public static void main(String[] args) {
		ObservableMsg msg = new ObservableMsg("lisi");
		msg.addObserver(new ObserverA());
		msg.addObserver(new ObserverB());

		msg.setName("zhangsan");
		msg.ChengA(new MsgA("hh", 12, 12.3));
	}
}
