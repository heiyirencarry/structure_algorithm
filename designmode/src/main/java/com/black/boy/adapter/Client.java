package com.black.boy.adapter;

/**
 * @author eBuy
 *
 */
public class Client {

	public static void main(String[] args) {
		Usb usb = new Adapter1(new TypeC() {
			@Override
			public void press() {
				System.out.println("这是TypeC的接口");
			}
		});
		// 将typec的接口适配成usb接口
		usb.socket();
	}
}
