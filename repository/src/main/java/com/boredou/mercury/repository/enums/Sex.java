package com.boredou.mercury.repository.enums;

public enum Sex {
	
	male("男"),female("女");
	
	private String value;

	private Sex(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
