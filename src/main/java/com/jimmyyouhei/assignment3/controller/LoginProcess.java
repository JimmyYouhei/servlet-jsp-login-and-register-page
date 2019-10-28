package com.jimmyyouhei.assignment3.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import com.jimmyyouhei.assignment3.bean.UserBean;
import com.jimmyyouhei.assignment3.bean.UsersList;
import com.jimmyyouhei.assignment3.reusable.ErrorCommand;
import com.jimmyyouhei.assignment3.reusable.RequestCommand;

@WebServlet("/loginProcess")
public class LoginProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String USERSFILE = "/WEB-INF/users.txt";

	Map<String, String> users ;
	
	UserBean userBean;
	
	UsersList usersLoggedIn = new UsersList();
	
    public LoginProcess() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usersPath = getServletContext().getRealPath(USERSFILE);
		boolean loginSuccess = false;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		

		
		File usersFile = new File(usersPath);
		
		readUsersFromFile(request, response, usersFile);
		
		if(users == null) {
			return;
		}
		
		if(username.strip().equals("") || password.strip().equals("")) {
			sendBackToLoginAndNotifyLoginError(request, response);
			return;
		} 
		
		for(String user : users.keySet()) {
			if(user.equals(username)) {
				
				if(password.equals(users.get(user))) {
					loginSuccess = true;
				}
				
			}
			
		}
		
		
		
		if(!loginSuccess) {
			sendBackToLoginAndNotifyLoginError(request, response);
			return;
		} else {
			login(request,response , username , password);
		}
		
		
	}


	private void login(HttpServletRequest request, HttpServletResponse response, String username, String password) throws ServletException, IOException {
	
		if(request.getServletContext().getAttribute("usersLoggedIn") != null) {
			
			usersLoggedIn = (UsersList) request.getServletContext().getAttribute("usersLoggedIn");
			
		} else {
			
		}
		
		if(request.getServletContext().getAttribute(username) != null) {
			
			readUserFromAppScope(request, username);
		
		} else {
			
			makeNewUserInAppScope(request, username, password);
		}
		
		request.getSession().setAttribute("userSession", userBean);
		request.getServletContext().setAttribute("usersLoggedIn", usersLoggedIn);
		
		if(userBean.getLoggedInTimes() == 1) {
			RequestCommand.setRequestAttributeToTrue(request, "firstLogin");
		}

		RequestCommand.redirectToPage(request, response, "index.jsp");
		return;
	}


	private void makeNewUserInAppScope(HttpServletRequest request, String username, String password) {
		userBean = new UserBean(username , password);
		userBean.setLoggedInTimes(1);
		
		usersLoggedIn.add(userBean);
		
		request.getServletContext().setAttribute(username, userBean);
	}


	private void readUserFromAppScope(HttpServletRequest request, String username) {
		userBean = (UserBean) request.getServletContext().getAttribute(username);
		userBean.setLoggedInTimes(userBean.getLoggedInTimes() + 1);
		
		request.getServletContext().setAttribute(username, userBean);
	}


	private void readUsersFromFile(HttpServletRequest request, HttpServletResponse response, File usersFile)
			throws IOException, ServletException, FileNotFoundException {
		if(!usersFile.exists() || usersFile.length() == 0) {
			sendBackToLoginAndNotifyNoUser(request, response, usersFile);
		} else {
			readUsers(usersFile);
		}
	}


	private void readUsers(File usersFile) throws FileNotFoundException {
		users = new LinkedHashMap<>() ;
		
		String username =null;
		String password = null;
		
		Scanner sc = new Scanner(usersFile);
		while(sc.hasNextLine()) {
			
			if(username == null) {
				username = sc.nextLine().substring(10);
			}else {
				password = sc.nextLine().substring(10);
				
				users.put(username, password);
				
				username = null;
				password = null;
				
			}
			
		}
		
		sc.close();
	}


	private void sendBackToLoginAndNotifyNoUser(HttpServletRequest request, HttpServletResponse response,
			File usersFile) throws IOException, ServletException {
		usersFile.createNewFile();
		ErrorCommand.notifyError(request, "noUser");
		RequestCommand.redirectToPage(request, response, "login.jsp");
		return;
	}

	private void sendBackToLoginAndNotifyLoginError(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ErrorCommand.notifyError(request, "loginError");
		RequestCommand.redirectToPage(request, response, "login.jsp");
		return;
		
	}
}

