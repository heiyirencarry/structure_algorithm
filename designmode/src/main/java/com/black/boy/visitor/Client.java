package com.black.boy.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author black boy
 *
 */
public class Client {
	public static void main(String[] args) {
		List<Person> list = new ArrayList<Person>();
		Success s = new Success();
		Man m = new Man();
		Woman w = new Woman();
		list.add(m);
		list.add(w);
		for (Person p : list) {
			p.accept(s);
		}
	}
}
