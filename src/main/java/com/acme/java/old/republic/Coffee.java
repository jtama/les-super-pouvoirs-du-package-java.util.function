package com.acme.java.old.republic;

public class Coffee extends Beverage {

	private Type type;

	public Type type() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public boolean isTarget() {
		return containsCaffeine() && !isFairTrade();
	}

	public enum Type {
		ROBUSTA,
		ARABICA
	}

}