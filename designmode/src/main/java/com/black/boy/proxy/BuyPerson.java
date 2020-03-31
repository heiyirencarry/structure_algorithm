package com.black.boy.proxy;

/**
 * @author eBuy
 *
 */
public class BuyPerson implements IBuyRoom {

	@Override
	public void proBuy() {
		System.out.println("真实购买者的买前处理");
	}

	@Override
	public void buy() {
		System.out.println("真实购买者付钱");
	}

	@Override
	public void afterBuy() {
		System.out.println("真实购买者买后的处理");
	}

}
