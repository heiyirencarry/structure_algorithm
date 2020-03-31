package com.black.boy.bridge;

/**
 * @author eBuy
 *
 */
public class Msg implements PhoneSoftware {

	private String name;

	public Msg(String name) {
		super();
		this.name = name;
	}

	@Override
	public void playSoftware() {
		System.out.println("软件的名称是：" + name);
	}

}
