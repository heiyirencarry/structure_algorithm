package com.black.boy.proxy;

import java.lang.reflect.Proxy;

/**
 * 动态代理的测试客户端
 * 
 * @author eBuy
 *
 */
public class Client2 {

	public static void main(String[] args) {
		BuyPerson buyPerson = new BuyPerson();
		LogHandler handler = new LogHandler(buyPerson);
		IBuyRoom proxy = (IBuyRoom) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[] { IBuyRoom.class },
				handler);
		proxy.afterBuy();
		proxy.buy();
	}
}
