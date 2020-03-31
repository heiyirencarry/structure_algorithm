package com.black.boy.status;

/**
 * 面向对象设计就是希望做到代码责任的分解，单一职责原则
 * 状态模式：当一个对象的内部状态改变时允许改变其行为，这个对象看起来像是改变了其类
 * 状态模式主要解决的是当控制一个对象状态转换的条件表达式过于复杂时的情况，把状态判断逻辑转移到表示不同状态的一系列类当中，可以把复杂的判断逻辑简化
 * @author eBuy
 *状态模式的有点：将特点的状态相关的行为都放到一个对象中，由于所有与状态相关的代码都存于某个对象状态对象中，所以通过定义新的子类可以很容易的增加新的状态和转换
 *目的就是消除庞大的条件分支语句，状态模式通过把各种状态转移逻辑分布到status的子类之间，来减少相互之间的依耐
 *当一个对象的行为取决于它的状态，并且它必须在运行时刻根据状态改变它的行为时，就可以考虑使用状态模式了
 */
public class StatusSimple {
	public static void main(String[] args) {
		Context context = new Context();
		context.setStatus(new StatusA());
		context.request();
		context.request();
		context.request();
		context.request();
	}
}
interface Status {
	void handler(Context ct);
}
class StatusA implements Status {
	@Override
	public void handler(Context ct) {
		ct.setStatus(new StatusB());
	}
}
class StatusB implements Status {
	@Override
	public void handler(Context ct) {
		ct.setStatus(new StatusA());
	}
}
class Context {
	private Status status;
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
		System.out.println("当前的状态是：" + this.status.getClass().getSimpleName());
	}
	public void request() {
		this.status.handler(this); 
	}
}