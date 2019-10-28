package com.jimmyyouhei.assignment3.reusable;

import java.io.IOException;
import java.net.http.HttpRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RequestCommand {
	
	public static void setRequestAttributeToTrue (HttpServletRequest request , String name) {
		request.setAttribute(name, true);
	} 
	
	public static void setSessionAttributeToTrue (HttpServletRequest request , String name) {
		request.getSession().setAttribute(name, true);
	} 
	
	public static void redirectToPage (HttpServletRequest request , HttpServletResponse response , String pageName) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(pageName);
		dispatcher.forward(request, response);

	}
	
	
	
}
