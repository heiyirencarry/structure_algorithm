package com.black.boy.adapter.newpkg;

/**
 * 在开发中，也就是系统的数据和行为都正确，但接口不符时，我们应该考虑使用适配器，目的是使控制范围之外的一个原有对象与某个接口匹配，适配模式主要应用于希望复用一些现有的类
 * 但是接口又与复用环境的接口要求不一致的情况
 * 将一个类的接口转换为客户希望的另一个类的接口，适配器模式可以使原本不兼容的类可以一起工作
 * eg： 我们的用电电电压220V，有的国家是110V，一个电脑就不能在任何的国家使用，这个时候就需要适配器
 * 
 * 类适配模式：通过多重继承对一个接口与另一个接口进行匹配
 * 对象适配器模式：Java是单继承语言，主要讲对象适配器
 */
public class Adapter {
	public static void main(String[] args) {
		Target t = new AdpCls();
		t.request();
	}
}

//目标对象，也就是可用期望使用的接口、类、抽象类
class Target {
	public void request() {
		System.out.println("目标对象的接口方法");
	}
}
//需要适配的类。接口
class OldClass {
	public void OldReq() {
		System.out.println("原有的方法");
	}
}
//适配器类
class AdpCls extends Target {
	private OldClass oldCls = new OldClass();
	
	public void request() {
		this.oldCls.OldReq();
	}
}
