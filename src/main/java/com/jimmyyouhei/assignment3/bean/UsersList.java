package com.jimmyyouhei.assignment3.bean;

import java.util.ArrayList;
import java.util.List;

public class UsersList {
	
	List<UserBean> usersList = new ArrayList<>();
	
	public UsersList() {
	}

	public UsersList(List<UserBean> usersList) {
		this.usersList = usersList;
	}

	public List<UserBean> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<UserBean> usersList) {
		this.usersList = usersList;
	}

	public void add(UserBean user) {
		usersList.add(user);
	}
	
	public String printUsersName() {
		
		StringBuilder sb = new StringBuilder();
		
		for (UserBean user: usersList) {
			sb.append(user.getUsername());
			sb.append(" , ");
		}
		
		sb.setLength(sb.length()-3);
		
		return sb.toString();
	}
	
	public String printTotalLoggedInTimes() {
		int result = 0;
		
		for (UserBean user: usersList) {
			result += user.getLoggedInTimes();
		}
		
		return String.valueOf(result);
	}

}
