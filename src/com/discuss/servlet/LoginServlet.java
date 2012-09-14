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
			
		if(userDao.loginOk(userName, userPassword)){
			System.out.println("ok");
			 request.setAttribute("loginSta", "true");
		}else{
			 request.setAttribute("loginSta", "false");
		}
		
//		//获取session
//		HttpSession session = request.getSession(true);
//		
//		//获取application
//		ServletContext application = this.getServletContext();

	    //获取本地地址
	    System.out.println("本地地址是："  + request.getLocalAddr());
	    
	    RequestDispatcher de = request.getRequestDispatcher("login.jsp");
	    de.forward(request, response);
	    
	}

}
