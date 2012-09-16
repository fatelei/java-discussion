package com.discuss.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.discuss.bean.SesVaBean;
import com.discuss.bean.SystemConfBean;
import com.discuss.bean.UserBean;
import com.discuss.dao.UserDao;
import com.discuss.util.JsonUtil;

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
		//System.out.println(request.getParameter("userFunFlag"));
		Integer userFunFlag = Integer.valueOf(request.getParameter("userFunFlag"));
		//session
		HttpSession session = request.getSession(true);
		
		//directed to un login page
		String path = request.getParameter("path");
		
		//parameter	values
		String userName = request.getParameter("username");
		String userPassword = request.getParameter("password");
		String userResPassword = request.getParameter("repassword");
		String userId = request.getParameter("userid");
		
		String userRank = request.getParameter("userrank");
		
		//split page values
		String	nowPage = request.getParameter("nowPage");
		
		//Json
		JSONObject json = null;
		
		switch(userFunFlag){
		case 1:					//login
			UserBean user = userDao.loginOk(userName, userPassword);
			if( user != null){
				 //System.out.println("ok");
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
			}else{
				 session.setAttribute("loginSta", "false");
				 response.sendRedirect("login.jsp");
			}
			break;
		case 2:					//add
			if(!userPassword.equals(userResPassword)){
				//password is not the same
				session.setAttribute("addSta", "false");
				response.sendRedirect("register.jsp");
			}else{
				UserBean newUser = new UserBean();
				newUser.setUserName(userName);
				newUser.setUserPassword(userPassword);
				newUser.setUserRank(3);
				if(userDao.addUsr(newUser)){
					//add ok
					session.setAttribute("addSta", "true");
					response.sendRedirect("login.jsp");
				}else{
					//name already in the database
					session.setAttribute("addSta", "false");
					response.sendRedirect("register.jsp");
				}
			}
			break;
		case 3:					//modify
			UserBean newUser = new UserBean();
			newUser.setUserId(Integer.valueOf(userId));
			newUser.setUserName(userName);
			newUser.setUserPassword(userPassword);
			newUser.setUserRank(Integer.valueOf(userRank));
			if(userDao.modifyUser(newUser)){
				//modify ok
				//System.out.println("modify ok");
				session.setAttribute("modifySta", "true");
				response.sendRedirect("user_manage.jsp");
			}else{
				//name already in the database
				//System.out.println("modify error");
				session.setAttribute("modifySta", "false");
				response.sendRedirect("user_manage.jsp");
			}
			break;
		case 4:					//del
			json = new JSONObject();
			if(userDao.delUser(Integer.valueOf(userId))){
				json.put("delSta", "true");
			} else {
				json.put("delSta", "false");
			}
			JsonUtil.sendJson(response, json.toString());
			break;
		case 5:					//logout
			session.invalidate();
			response.sendRedirect("index.jsp");
			break;
		case 6:					//query
			int totalUser = userDao.countUser();
			int totalPage = 0;
			if(totalUser % SystemConfBean.UserListPageNum == 0){
				totalPage = totalUser / SystemConfBean.UserListPageNum;
			} else {
				totalPage = totalUser / SystemConfBean.UserListPageNum + 1;
			}
			
			ArrayList<UserBean> users = userDao.queryUser(Integer.valueOf(nowPage), SystemConfBean.UserListPageNum);
			
			//bulid	json oject
			json = new JSONObject();   
			JSONArray useArray = new JSONArray();   
	        JSONObject oneTemp = null;   
	        for(UserBean oneUser : users){
	        	oneTemp = new JSONObject();   
	        	oneTemp.put("id", oneUser.getUserId());   
	        	oneTemp.put("name", oneUser.getUserName());   
	        	useArray.add(oneTemp); 
	        }     
	        json.put("totalPage", totalPage);   
	        json.put("users", useArray); 
	        
	        //System.out.println(json);
	        
	        JsonUtil.sendJson(response, json.toString());
	        break;
		case 7:
			String queryUser = "select * from " + UserBean.UserTable + " where " + UserBean.UserID + "='" + userId + "'";
			ArrayList<UserBean> curUser = userDao.findUserList(queryUser);
			json = new JSONObject();
			for (UserBean cuser: curUser) {
				json.put("id", cuser.getUserId());
				json.put("name", cuser.getUserName());
				json.put("password", cuser.getUserPassword());
				json.put("rank", cuser.getUserRank());
			}
			JsonUtil.sendJson(response, json.toString());
			break;
		}
	    
	}
}
