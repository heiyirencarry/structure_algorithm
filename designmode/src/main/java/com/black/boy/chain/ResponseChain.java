package com.black.boy.chain;

/**
 * 模版方法和责任链实现一个请假审批的流程流转
 * @author eBuy
 *
 */
public class ResponseChain {
	
	public static void main(String[] args) {
		BoltData qj = new BoltData("请假", 1);
		BoltData sz = new BoltData("升职", 0);
		JinLi jl = new JinLi("张三");
		LaoBan lb = new LaoBan("李毅");
		jl.setChainObj(lb);
		
		jl.execute(qj);
		System.out.println("-------------------------");
		jl.execute(sz);
	}
}

//传递的对象
class BoltData {
	private String type;
	private int time;
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	public BoltData(String type, int time) {
		this.type = type;
		this.time = time;
	}
	public BoltData() {
	}
}

abstract class IChain {
	private String name;
	private IChain chainObj;
	public void setChainObj(IChain chainObj) {
		this.chainObj = chainObj;
	}
	public IChain() {}
	public IChain(String name) {this.name = name;}
	public void execute(BoltData data) {
		if(isHandle(data)) {
			System.out.println("处理人["+this.name+"]处理了这个请求");
		} else {
			if(chainObj != null) 
				chainObj.execute(data);
			else
				System.out.println("没有人处理了");
		}
	}
	public abstract boolean isHandle(BoltData data);
}

class JinLi extends IChain {
	public JinLi(String name) {
		super(name);
	}

	@Override
	public boolean isHandle(BoltData data) {
		return "请假".equals(data.getType()) && data.getTime() <= 2;
	}
	
}

class LaoBan extends IChain {
	public LaoBan(String name) {
		super(name);
	}

	@Override
	public boolean isHandle(BoltData data) {
		return true;
	}
	
	//如果有特殊的处理可以重写 execute 方法
}

