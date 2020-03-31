package com.black.boy.command;

/**
 * 命令模式：将一个请求封装为一个对象，从而使你可用不同的请求对客户进行参数化，
 * 对请求排队、记录、撤销、修改支持操作
 * 优点：1、比较容易的设计一个命令队列
 * 2、可以比较容易的将命令的操作记入日志
 * 3、允许接收请求的一方决定是否要否决该请求
 * 4、可以容易的实现请求的撤销和重做
 * 5、新增新的命令类不会影响其它的类，扩展命令能力很容易
 *  命令模式就是把请求一个操作的对象和知道怎么执行一个操作的对象分开
 * @author eBuy
 *
 */
public class Command {
	
	public static void main(String[] args) {
		Receiver r = new Receiver();
		AbsCommand c = new ActualCommand(r);
		Invoker i = new Invoker();
		i.setCommand(c);
		i.notifyDoit();
	}
}

//命令的执行者
class Receiver {
	
	public void active() {
		System.out.println("执行了请求，做相应的逻辑处理");
	}
}

//命令的抽象类
abstract class AbsCommand{

	protected Receiver receiver;
	protected AbsCommand(Receiver receiver) {
		this.receiver = receiver;
	}
	
	protected abstract void execute();
}

//命令的实现类 执行者有多少个能力就有多少个实现类
class ActualCommand extends AbsCommand{

	public ActualCommand(Receiver receiver) {
		super(receiver);
	}

	@Override
	protected void execute() {
		receiver.active();
	}
}

//命令的传递者
class Invoker {
	private AbsCommand command;

	public void setCommand(AbsCommand command) {
		this.command = command;
	}
	
	public void notifyDoit() {
		this.command.execute();
	}
	
}