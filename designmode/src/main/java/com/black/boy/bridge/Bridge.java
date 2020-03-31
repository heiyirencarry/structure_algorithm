package com.black.boy.bridge;

public class Bridge {

	public static void main(String[] args) {
		
	}
}

//Implementor 实施者  维度一
abstract class Implementor {
	public abstract void operation();
}

class ConcreteImplementorA extends Implementor {
	@Override
	public void operation() {
		System.out.println("具体实现A的方法执行");
	}
}
class ConcreteImplementorB extends Implementor {
	@Override
	public void operation() {
		System.out.println("具体实现B的方法执行");
	}
}

//维度二    持有维度一的引用  两个维度可以自由的增加改变
class Abstraction {
	protected Implementor implementor;

	public void setImplementor(Implementor implementor) {
		this.implementor = implementor;
	}
	
	public void operation() {
		implementor.operation();
	}
}

class RefinedAbstraction extends Abstraction {
	//覆盖父类的方法
	public void operation() {
		//可以添加自己的逻辑
		super.operation();
//		implementor.operation();
	}
}