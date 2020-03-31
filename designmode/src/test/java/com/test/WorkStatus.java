package com.test;

public class WorkStatus {

	public static void main(String[] args) {
		Work work = new Work();
		work.setTime(20);
		work.showWorkStatus();
		String simpleName = work.getStatus().getClass().getSimpleName();
		System.out.println(simpleName);
	}
}


class Work{
	
	private int time;
	private boolean isWork; 
	private Status status; 
	
	public Work() {
		this.status = new MonStatus();
	}
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public boolean isWork() {
		return isWork;
	}

	public void setWork(boolean isWork) {
		this.isWork = isWork;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void showWorkStatus() {
		status.showStatus(this);
	}
	
}

interface Status {
	
	void showStatus(Work work);
}

class MonStatus implements  Status{

	@Override
	public void showStatus(Work work) {
		if(work.getTime() < 12) {
			System.out.println("是早上的工作");
		}else {
			new AfterStatus().showStatus(work);
//			work.setStatus(new AfterStatus());
//			work.showWorkStatus();
		}
	}
}

class AfterStatus implements Status{

	@Override
	public void showStatus(Work work) {
		if(work.getTime() < 22) {
			System.out.println("是下午");
		}else {
			work.setWork(false);
			System.out.println("结束了");
		}
	}
	
}