package com.discuss.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.discuss.dao.UserDao;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserDao userDao = new UserDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("username");
		String userPassword = request.getParameter("password");
		 
		RequestDispatcher de = null;
		if(userDao.loginOk(userName, userPassword)){
			System.out.println("ok");
			 request.setAttribute("loginSta", "true");
			 de = request.getRequestDispatcher("index.jsp");
		}else{
			 request.setAttribute("loginSta", "false");
			 de = request.getRequestDispatcher("login.jsp");
		}
		
<<<<<<< HEAD
//		//get session
//		HttpSession session = request.getSession(true);
//		
//		//get application
//		ServletContext application = this.getServletContext();

	    System.out.println("local address："  + request.getLocalAddr());
	    	    
=======
//		//��ȡsession
//		HttpSession session = request.getSession(true);
//		
//		//��ȡapplication
//		ServletContext application = this.getServletContext();

	    //��ȡ���ص�ַ
	    System.out.println("ip address"  + request.getLocalAddr());
	    
	    RequestDispatcher de = request.getRequestDispatcher("login.jsp");
>>>>>>> 139b2190ab1612699c2523e89b10c8895c567e7a
	    de.forward(request, response);
	}
}
