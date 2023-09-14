package com.acme.java.old.republic;

public abstract class Item {

	private String description;

	public abstract boolean isSuitableForChildren();

	public abstract boolean isTarget();

	public String description() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
