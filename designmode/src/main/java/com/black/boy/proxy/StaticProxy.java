package com.black.boy.proxy;

/**
 * @author eBuy
 *
 */
public class StaticProxy implements IBuyRoom {

	private IBuyRoom realBuy;

	public StaticProxy(IBuyRoom realBuy) {
		this.realBuy = realBuy;
	}

	@Override
	public void proBuy() {
		System.out.println("代理的购买前处理");
	}

	@Override
	public void buy() {
		realBuy.buy();
	}

	@Override
	public void afterBuy() {
		System.out.println("代理的购买后处理");
	}

}
