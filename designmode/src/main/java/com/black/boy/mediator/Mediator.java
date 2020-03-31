package com.black.boy.mediator;

public class Mediator {

	public static void main(String[] args) {
		ConcreteMediator m = new ConcreteMediator();
		ConcreteColleague1 c1 = new ConcreteColleague1(m);
		ConcreteColleague2 c2 = new ConcreteColleague2(m);
		ConcreteColleague3 c3 = new ConcreteColleague3(m);
		
		m.setC1(c1);
		m.setC2(c2);
		m.setC3(c3);
		
		c1.send("c1");
		c2.send("c2");
		c3.send("c3");
	}
}

/**
 * 抽象的中介者
 * @author eBuy
 *
 */
interface IMediator{
	void send(String msg, Colleague colleague);
}

/**
 * 中介者实现类
 */
class ConcreteMediator implements IMediator {
	private ConcreteColleague1 c1;
	private ConcreteColleague2 c2;
	private ConcreteColleague3 c3;
	public void setC1(ConcreteColleague1 c1) {
		this.c1 = c1;
	}
	public void setC2(ConcreteColleague2 c2) {
		this.c2 = c2;
	}
	public void setC3(ConcreteColleague3 c3) {
		this.c3 = c3;
	}

	@Override
	public void send(String msg, Colleague colleague) {
		//同事之间的信息交互关系都在中介对象的这个方法中实现
		if(colleague instanceof ConcreteColleague1) {
			c2.notify(msg);
		}else if(colleague instanceof ConcreteColleague2) {
			c1.notify(msg);
			c3.notify(msg);
		}else if(colleague instanceof ConcreteColleague3) {
			c2.notify(msg);
		}
	}
}

/**
 * 抽象的同事
 * @author eBuy
 *
 */
abstract class Colleague {
	protected IMediator mediator; //每一个同事都认识中介者，都和中介者通信达到和其它同事通信的目的
	public abstract void notify(String msg);
	public Colleague(IMediator mediator) {
		this.mediator = mediator;
	}
}

class ConcreteColleague1 extends Colleague {
	public ConcreteColleague1(IMediator mediator) {
		super(mediator);
	}
	
	public void send(String msg) {
		mediator.send(msg, this);
	}
	
	public void notify(String msg) {
		System.out.println("同事1得到的消息：" + msg);
	}
}

class ConcreteColleague2 extends Colleague {
	public ConcreteColleague2(IMediator mediator) {
		super(mediator);
	}
	
	public void send(String msg) {
		mediator.send(msg, this);
	}
	
	public void notify(String msg) {
		System.out.println("同事2得到的消息：" + msg);
	}
}

class ConcreteColleague3 extends Colleague {
	public ConcreteColleague3(IMediator mediator) {
		super(mediator);
	}
	
	public void send(String msg) {
		mediator.send(msg, this);
	}
	
	public void notify(String msg) {
		System.out.println("同事3得到的消息：" + msg);
	}
}