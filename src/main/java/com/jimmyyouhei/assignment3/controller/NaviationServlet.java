package com.jimmyyouhei.assignment3.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jimmyyouhei.assignment3.reusable.RequestCommand;


@WebServlet("/navigationServlet")
public class NaviationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NaviationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String commandParam = request.getParameter("command");
		
		switch( commandParam) {
			case "landing":
				toLoginPage(request,response);
				break;
				
			case "profile":
				toProfilePage(request,response);
				break;
			
			case "mainPage":
				toHomePage(request , response);
				break;
				
			case "userCounter":
				toUserCounterPage(request , response);
				break;
				
			case "logout":
				logout(request,response);
				break;
				
			case "signUp":
				signUpPage(request,response);
				break;
		
		}
		
	}

	private void signUpPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestCommand.redirectToPage(request, response, "sign-up.jsp");
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("userSession");
		RequestCommand.setRequestAttributeToTrue(request, "logout");
		RequestCommand.redirectToPage(request, response, "login.jsp");
		
	}

	private void toUserCounterPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestCommand.redirectToPage(request, response, "userCounter.jsp");
		
	}

	private void toHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestCommand.redirectToPage(request, response, "index.jsp");
		
	}

	private void toProfilePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestCommand.redirectToPage(request, response, "profile.jsp");
		
	}

	private void toLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestCommand.redirectToPage(request, response, "login.jsp");
		
	}

}
