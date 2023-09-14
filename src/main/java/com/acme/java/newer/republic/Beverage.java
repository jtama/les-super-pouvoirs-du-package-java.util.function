package com.acme.java.newer.republic;

public abstract class Beverage extends Item {

	private String name;

	private boolean caffeine;

	private boolean fsc;

	private boolean fairTrade;

	private String description;

	@Override
	public boolean isSuitableForChildren() {
		return !containsCaffeine();
	}

	public String description() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String name() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean containsCaffeine() {
		return caffeine;
	}

	public void setCaffeine(boolean caffeine) {
		this.caffeine = caffeine;
	}

	public boolean comesFromFsc() {
		return fsc;
	}

	public void setFsc(boolean fsc) {
		this.fsc = fsc;
	}

	public boolean isFairTrade() {
		return fairTrade;
	}

	public void setFairTrade(boolean fairTrade) {
		this.fairTrade = fairTrade;
	}

}