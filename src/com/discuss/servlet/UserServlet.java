package com.discuss.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.discuss.dao.UserDao;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserDao userDao = new UserDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//function flags
		Integer userFunFlag = Integer.valueOf(request.getParameter("userFunFlag"));
		RequestDispatcher de = null;		//control forward
		
		switch(userFunFlag){
		case 1:					//login
			String userName = request.getParameter("username");
			String userPassword = request.getParameter("password");
			 
			
			if(userDao.loginOk(userName, userPassword)){
				System.out.println("ok");
				 request.setAttribute("loginSta", "true");
				 de = request.getRequestDispatcher("index.jsp");
			}else{
				 request.setAttribute("loginSta", "false");
				 de = request.getRequestDispatcher("login.jsp");
			}
			
	//		//get session
	//		HttpSession session = request.getSession(true);
	//		
	//		//get application
	//		ServletContext application = this.getServletContext();
			break;
			
		}
		
	    de.forward(request, response);
	}
}
