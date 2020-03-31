package com.black.boy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 用代理对象实现日志的打印
 * 
 * @author eBuy
 *
 */
public class LogHandler implements InvocationHandler {

	// 被代理的对象
	private IBuyRoom buy;

	public LogHandler(IBuyRoom buy) {
		this.buy = buy;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("---日志操作前---");
//		Object invoke = method.invoke(buy, args);
		buy.buy();
		System.out.println("---日志操作后---");
		return null;
	}

}
