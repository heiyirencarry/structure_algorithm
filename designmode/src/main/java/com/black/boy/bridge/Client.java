package com.black.boy.bridge;

/**
 * @author eBuy
 *
 */
public class Client {

	public static void main(String[] args) {
		Msg msg = new Msg("发短信的功能");

		MiPhone mi = new MiPhone("小米");
		mi.addSoftware(msg);
		mi.addSoftware(new PhoneGame("LOL"));
		mi.playSoftware();
	}
}
