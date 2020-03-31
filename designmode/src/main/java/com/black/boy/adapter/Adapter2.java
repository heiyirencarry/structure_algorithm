package com.black.boy.adapter;

/**
 * @author eBuy 针对类的适配，java是单继承的语言，如果采用这种方式不能继承其它的类
 *         不是很灵活，所以将继承的方式改为组合，一般情况继承都可以使用组合替换
 */
public class Adapter2 extends TypeCImpl implements Usb {

	@Override
	public void socket() {
		super.press();
	}

}
