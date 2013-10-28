package com.wzlee.hgm123.view;
public class AccessToken {
	private String access_token;
	private Integer expires_in;
	private String refresh_token;
	private String token_type;
	
	public AccessToken() {
		super();
	}
	public AccessToken(String access_token, Integer expires_in,
			String refresh_token, String token_type) {
		super();
		this.access_token = access_token;
		this.expires_in = expires_in;
		this.refresh_token = refresh_token;
		this.token_type = token_type;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public Integer getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	@Override
	public String toString() {
		return "AccessToken [access_token=" + access_token + ", expires_in="
				+ expires_in + ", refresh_token=" + refresh_token
				+ ", token_type=" + token_type + "]";
	}
	
}
