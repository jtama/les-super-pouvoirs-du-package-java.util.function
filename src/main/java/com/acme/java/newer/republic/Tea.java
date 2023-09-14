package com.acme.java.newer.republic;

public class Tea extends Beverage {

	private Type type;

	public Type type() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public enum Type {
		GREEN,
		BLACK,
		WHITE,
		RED
	}

}