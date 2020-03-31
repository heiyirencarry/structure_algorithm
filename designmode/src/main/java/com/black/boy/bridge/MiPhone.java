package com.black.boy.bridge;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eBuy
 *
 */
public class MiPhone implements PhoneBrand {

	private String name;
	private List<PhoneSoftware> list = new ArrayList<PhoneSoftware>();

	public MiPhone(String name) {
		super();
		this.name = name;
	}

	@Override
	public void showName() {
		System.out.println("手机的品牌是：" + name);
	}

	public void addSoftware(PhoneSoftware sfw) {
		list.add(sfw);
	}

	public void playSoftware() {
		for (PhoneSoftware phoneSoftware : list) {
			phoneSoftware.playSoftware();
		}
	}

}
