package com.black.boy.proxy;

/**
 * @author eBuy
 *
 */
public interface IBuyRoom {

	/**
	 * 买房子之前的准备处理
	 */
	void proBuy();

	/**
	 * 付钱买房子
	 */
	void buy();

	/**
	 * 付钱之后的收尾处理
	 */
	void afterBuy();
}
