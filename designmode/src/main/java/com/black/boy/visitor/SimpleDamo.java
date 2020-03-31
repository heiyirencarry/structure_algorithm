package com.black.boy.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author black boy
 */
public class SimpleDamo {

	public static void main(String[] args) {
		List<Person> list = new ArrayList<Person>();
		Man m = new SimpleDamo().new Man();
		m.setAction("成功");
		list.add(m);
		Woman w = new SimpleDamo().new Woman();
		w.setAction("失败");
		list.add(w);

		for (Person p : list) {
			p.getConclusion();
		}
	}

	abstract class Person {
		private String action;

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}

		public abstract void getConclusion();
	}

	/**
	 * 这种if条件的判断是可以消除的，不希望代码中有这样的条件分支 因为这样的分支但凡新增一种状态就需要修改源码
	 * 失败，成功是一种状态可以单独抽取出一个类，然后传入委托调用
	 */
	class Man extends Person {
		@Override
		public void getConclusion() {
			if (getAction().equals("成功")) {
				System.out.println(this.getClass().getSimpleName() + "成功");
			} else if (getAction().equals("失败")) {
				System.out.println(this.getClass().getSimpleName() + "失败");
			} else if (getAction().equals("恋爱")) {
				System.out.println(this.getClass().getSimpleName() + "恋爱");
			}
		}
	}

	class Woman extends Person {
		@Override
		public void getConclusion() {
			if (getAction().equals("成功")) {
				System.out.println(this.getClass().getSimpleName() + "成功");
			} else if (getAction().equals("失败")) {
				System.out.println(this.getClass().getSimpleName() + "失败");
			} else if (getAction().equals("恋爱")) {
				System.out.println(this.getClass().getSimpleName() + "恋爱");
			}
		}

	}
}
