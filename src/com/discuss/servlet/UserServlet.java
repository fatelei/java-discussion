package com.discuss.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.discuss.bean.SesVaBean;
import com.discuss.bean.UserBean;
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
		//control forward
		RequestDispatcher de = null;
		//session
		HttpSession session = request.getSession(true);
		//System.out.println(userFunFlag);
		String path = request.getParameter("path");
		
		//parameter	values
		String userName = request.getParameter("username");
		String userPassword = request.getParameter("password");
		
		switch(userFunFlag){
		case 1:					//login
			UserBean user = userDao.loginOk(userName, userPassword);
			if( user != null){
				 System.out.println("ok");
				 request.setAttribute("loginSta", "true");
				 session.setAttribute(SesVaBean.username, userName);
				 //set login statue	in session
				 session.setAttribute(SesVaBean.LoginState, SesVaBean.LoginStateLogin);
				 switch(user.getUserRank()){
				 case 1: 
					 session.setAttribute(SesVaBean.UserRank, SesVaBean.UserRankAdmin);	
					 break;
				 case 2: 
					 session.setAttribute(SesVaBean.UserRank, SesVaBean.UserRankDepa);	
					 break;
				 case 3: 
					 session.setAttribute(SesVaBean.UserRank, SesVaBean.UserRankResd);	
					 break;					 
				 }
				 if (path == null) {
					 response.sendRedirect("index.jsp");
				 } else {
					 response.sendRedirect(path);
				 }
				 //de = request.getRequestDispatcher("index.jsp");
			}else{
				 request.setAttribute("loginSta", "false");
				 de = request.getRequestDispatcher("login.jsp");
				 de.forward(request, response);
			}
			break;
		case 2:					//add
			
			break;
		case 3:					//modify
			
			break;
		case 4:					//del
			
			break;
		case 5:					//logout
			session.invalidate();
			response.sendRedirect("index.jsp");
			break;
		case 6:					//query
			
			break;
		}
	    
	}
}
