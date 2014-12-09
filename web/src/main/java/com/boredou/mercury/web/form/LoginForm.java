package com.boredou.mercury.web.form;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;

public class LoginForm {

	@Getter
	@Setter
	private String userName;

	@Getter
	@Setter
	private String password;

	@Getter
	@Setter
	private String redirectURL;

	public AuthenticationToken createAuthenticationToken() {
		return new UsernamePasswordToken(userName, new Md5Hash(password).toHex());
	}

}
