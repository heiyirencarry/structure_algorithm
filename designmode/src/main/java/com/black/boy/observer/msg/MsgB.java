package com.black.boy.observer.msg;

/**
 * @author eBuy
 *
 */
public class MsgB {

	private String name;
	private int age;
	private double price;

	/**
	 * @param name
	 * @param age
	 * @param price
	 */
	public MsgB(String name, int age, double price) {
		super();
		this.name = name;
		this.age = age;
		this.price = price;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

}
