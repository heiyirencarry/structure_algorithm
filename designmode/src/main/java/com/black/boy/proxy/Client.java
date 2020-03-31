package com.black.boy.proxy;

/**
 * @author eBuy
 *
 */
public class Client {

	public static void main(String[] args) {
		StaticProxy proxy = new StaticProxy(new BuyPerson());
		proxy.proBuy();
		proxy.buy();
		proxy.afterBuy();
	}
}
