package com.black.boy.adapter.newpkg;

public class Adapter1 {
	public static void main(String[] args) {
		
	}
}
interface IUsb {
	void Usb();
}
interface ITypeC {
	void typeC();
}
class TypecImpl implements ITypeC {
	@Override
	public void typeC() {
		System.out.println("typec running..");
	}
}
//将typec和usb适配
class Adp1 extends TypecImpl implements IUsb {
	@Override
	public void Usb() {
		super.typeC();
	}
}
//建议使用组合的方式进行适配
class Adp2 implements IUsb {
	private ITypeC typeC = new TypecImpl();
	@Override
	public void Usb() {
		this.typeC.typeC();
	}
}