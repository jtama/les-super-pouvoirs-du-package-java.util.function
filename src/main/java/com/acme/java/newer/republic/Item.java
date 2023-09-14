package com.acme.java.newer.republic;

public abstract class Item {

	private String description;

	public abstract boolean isSuitableForChildren();

	public String description() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
