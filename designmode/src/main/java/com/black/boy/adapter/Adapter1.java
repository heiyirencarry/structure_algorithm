package com.black.boy.adapter;

/**
 * @author eBuy
 *
 */
public class Adapter1 implements Usb {

	private TypeC typeC;

	@Override
	public void socket() {
		typeC.press();
	}

	public Adapter1(TypeC typeC) {
		this.typeC = typeC;
	}

}
