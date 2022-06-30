package com.cybage.dto;

public class LoginDetails {

	private String email;
	private String password;
	private Integer otp;

	public LoginDetails() {

	}

	public LoginDetails(String email, String password, Integer otp) {
		super();
		this.email = email;
		this.password = password;
		this.otp = otp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "LoginDetails [email=" + email + ", password=" + password + ", otp=" + otp + "]";
	}

}
