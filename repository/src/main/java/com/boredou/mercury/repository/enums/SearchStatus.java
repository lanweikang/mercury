package com.boredou.mercury.repository.enums;

public enum SearchStatus {
	
	searching("攀爬中"),complete("静止");
	
	private String value;

	private SearchStatus(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	
}
