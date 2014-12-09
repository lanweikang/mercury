package com.boredou.mercury.repository.enums;

public enum FetchStatus {
	
	fetching("正在拉取数据"),complete("拉取数据完成");
	
	private String value;

	private FetchStatus(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
