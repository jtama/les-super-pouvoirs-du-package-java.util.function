package com.acme.java.old.republic;

public class Tea extends Beverage {

	private Type type;

	public Type type() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public boolean isTarget() {
		return comesFromFsc() && type == Type.RED;
	}

	public enum Type {
		GREEN,
		BLACK,
		WHITE,
		RED
	}

}