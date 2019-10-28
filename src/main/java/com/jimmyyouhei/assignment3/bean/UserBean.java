package com.jimmyyouhei.assignment3.bean;

public class UserBean {
	private String username;
	private String password;
	
	private Integer loggedInTimes;
	
	public UserBean() {
		
	}
	
	
	public UserBean(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}


	public Integer getLoggedInTimes() {
		return loggedInTimes;
	}


	public void setLoggedInTimes(Integer loggedInTimes) {
		this.loggedInTimes = loggedInTimes;
	}

	
	
	
}
