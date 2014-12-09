package com.boredou.mercury.repository.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class UserDO implements Serializable {

	private static final long serialVersionUID = -4302647678291998812L;

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String userName;

	@Getter
	@Setter
	private String password;
	
	@Setter
	@Getter
	private Date createDate;
	
	@Getter
	@Setter
	private int status;




}
