package com.jimmyyouhei.assignment3.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jimmyyouhei.assignment3.reusable.RequestCommand;

@WebServlet("/signUpProcess")
public class SignUpProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String USERSFILE = "/WEB-INF/users.txt";

	Map<String, String> users ;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpProcess() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean signUpSuccess = true;
		
		String usersPath = getServletContext().getRealPath(USERSFILE);
		
		File usersFile = new File(usersPath);
		
		readUsersFromFile(request, response, usersFile);
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username.strip().length()<=6 || username.matches(".*[$#@%^&*]+.*")) {
			signUpSuccess = false;
			RequestCommand.setRequestAttributeToTrue(request, "usernameError");
		} else {
			
			if(usersFile.length() == 0) {
				
			} else {
				if(users.containsKey(username)) {
					signUpSuccess = false;
					RequestCommand.setRequestAttributeToTrue(request, "usernameExist");
					
				}
				
			}
			
		}
		
		if(password.length()<= 8 || password.matches("[a-z\\s]+")) {
			signUpSuccess = false;
			RequestCommand.setRequestAttributeToTrue(request, "passwordError");
		}
		
		
		if(signUpSuccess == false) {
			RequestCommand.redirectToPage(request, response, "sign-up.jsp" );
		} else {
			
			writeToFile(usersFile, username, password);
			
			if(users.size()>1) {
				RequestCommand.redirectToPage(request, response, "/");
			} else {
				RequestCommand.setRequestAttributeToTrue(request, "firstSignUp");
				RequestCommand.redirectToPage(request, response, "login.jsp");
			}
			


		}
		
	}

	private void writeToFile(File usersFile, String username, String password) throws IOException {
		users.put(username, password);
		
		FileWriter writer = new FileWriter(usersFile);
		PrintWriter printWriter = new PrintWriter(writer);
		
		for(String key : users.keySet()) {
			printWriter.println("username: " +key);
			printWriter.println("password: " + users.get(key));
		}
		
		printWriter.close();
		

		
	}
	
	private void readUsersFromFile(HttpServletRequest request, HttpServletResponse response, File usersFile)
			throws IOException, ServletException, FileNotFoundException {
		if(!usersFile.exists() || usersFile.length() == 0) {
			usersFile.createNewFile();
			users = new LinkedHashMap<>();
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

}
