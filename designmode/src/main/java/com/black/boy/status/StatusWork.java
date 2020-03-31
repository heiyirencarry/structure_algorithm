package com.black.boy.status;

public class StatusWork {
	public static void main(String[] args) {
		Work work = new Work();
		work.setCurrentTime(10);
		work.handler();
	}
}
//工作
class Work {
	private int currentTime;
	private boolean finish = false;
	private WorkStatus status; //这个需要初始化
	public Work() {
		status = new MorningStauts();
	}
	public int getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}
	public boolean isFinish() {
		return finish;
	}
	public void setFinish(boolean finish) {
		this.finish = finish;
	}
	public WorkStatus getStatus() {
		return status;
	}
	public void setStatus(WorkStatus status) {
		this.status = status;
	}
	//请记住这种实现
	public void handler() {
		this.status.WriteProgram(this);
	}
}
//工作状态的接口
interface WorkStatus {
	void WriteProgram(Work work);
}
class MorningStauts implements WorkStatus {

	@Override
	public void WriteProgram(Work work) {
		if(work.getCurrentTime() < 12) {
			System.out.println("当前时间["+work.getCurrentTime()+"]" + work.getStatus().getClass().getSimpleName());
		} else {
		    work.setStatus(new NoonStatus());
//		    work.getStatus().WriteProgram(work);
		    work.handler();
		}
	}
}
class NoonStatus implements WorkStatus {
	@Override
	public void WriteProgram(Work work) {
		if(work.getCurrentTime() < 21) {
			System.out.println("当前时间["+work.getCurrentTime()+"]" + work.getStatus().getClass().getSimpleName());
		} else {
			work.setStatus(new StopStatus());
//		    work.getStatus().WriteProgram(work);
			work.handler();//work类的实现就可以这样调用很好用的方式，双方都引用了
		}
	}
}
class StopStatus implements WorkStatus {
	@Override
	public void WriteProgram(Work work) {
		System.out.println("必须下班了");
	}
}