package com.black.boy.bridge;

/**
 * @author eBuy
 *
 */
public class PhoneGame implements PhoneSoftware {

	private String gameName;

	public PhoneGame(String gameName) {
		super();
		this.gameName = gameName;
	}

	@Override
	public void playSoftware() {
		System.out.println("游戏软件名字是：" + gameName);
	}

}
