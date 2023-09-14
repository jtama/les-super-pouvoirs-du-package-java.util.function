package com.acme.java.newer.republic;

public class TeeShirt extends Item {

	private Size size;

	public Size size() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	@Override
	public boolean isSuitableForChildren() {
		return size == Size.XS;
	}

	public enum Size {
		XS,
		S,
		M,
		L,
		XL
	}

}