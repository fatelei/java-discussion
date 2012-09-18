package com.discuss.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.discuss.bean.DisObjBean;
import com.discuss.bean.SesVaBean;
import com.discuss.dao.DisObjectDao;

public class DisObjServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int funFlag = Integer.valueOf(request.getParameter("disFunFlag"));  
		
		HttpSession session = request.getSession();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		DisObjectDao disObjDao = new DisObjectDao();
		
		switch(funFlag){
		case 1:
			int userId = Integer.valueOf((String)session.getAttribute(SesVaBean.UserId));
			DisObjBean disObj = new DisObjBean(title, content, userId);
			disObjDao.addObject(disObj);
			
			break;
		}
		
		
		
	}

}
