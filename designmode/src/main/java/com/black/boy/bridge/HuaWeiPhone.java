package com.black.boy.bridge;

/**
 * @author eBuy
 *
 */
public class HuaWeiPhone implements PhoneBrand {

	private String name;

	public HuaWeiPhone(String name) {
		super();
		this.name = name;
	}

	@Override
	public void showName() {
		System.out.println("手机的品牌是：" + name);
	}

}
