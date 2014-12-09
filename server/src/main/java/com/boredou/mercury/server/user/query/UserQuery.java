package com.boredou.mercury.server.user.query;

import org.springframework.stereotype.Service;

import com.boredou.mercury.server.base.AbstractQuery;

@Service
public class UserQuery extends AbstractQuery {

	@Override
	protected void init() {
		super.init();
		System.out.println("===========UserQuery===============");
	}
}