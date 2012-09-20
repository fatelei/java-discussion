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

import com.discuss.bean.DisObjBean;
import com.discuss.bean.SesVaBean;
import com.discuss.bean.SysConfBean;
import com.discuss.dao.DisObjectDao;
import com.discuss.util.JsonUtil;

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
		String postId = request.getParameter("postId");
		
		DisObjectDao disObjDao = new DisObjectDao();
		JSONObject json = null;
		
		switch(funFlag){
		case 1: //post a new discuss object
			int userId = (Integer)session.getAttribute(SesVaBean.UserId);
			DisObjBean disObj = new DisObjBean(title, content, userId);
			if (disObjDao.addObject(disObj)) {
				session.setAttribute("post", "true");
				response.sendRedirect("detail.jsp?postId="+disObj.getDisObjID()+"&disFunFlag=2");
			} else {
				session.setAttribute("post", "false");
				response.sendRedirect("post.jsp");
			}
			break;
		case 2: //query a specific discuss
			DisObjBean curDis = disObjDao.queryObjById(Integer.parseInt(postId));
			json = new JSONObject();
			json.put("id", curDis.getDisObjID());
			json.put("title", curDis.getDisObjTopic());
			json.put("content", curDis.getDisObjContent());
			json.put("time", curDis.getDisObjRelTime());
			json.put("author", curDis.getDisObjUser().getUserName());
			JsonUtil.sendJson(response, json.toString());
			break;
		case 3: //query list of discuss
			int nowPage = Integer.parseInt(request.getParameter("nowPage"));
			String orderBy = request.getParameter("orderBy");
			int isAsc = Integer.parseInt(request.getParameter("isAsc"));
			ArrayList<DisObjBean> listDis = null;
			if (isAsc == 1) {
				listDis = disObjDao.queryObj(nowPage, 1, orderBy, true);
			} else {
				listDis = disObjDao.queryObj(nowPage, 1, orderBy, false);
			}
			int totalPages = 0;
			int totalDises = disObjDao.countObj();
			if (totalDises % SysConfBean.DisListPageNum == 0) {
				totalPages = totalDises / SysConfBean.DisListPageNum;
			} else {
				totalPages = totalDises / SysConfBean.DisListPageNum + 1;
			}
			JSONArray disAry = new JSONArray();
			for (DisObjBean perDis: listDis) {
				JSONObject tmp = new JSONObject();
				tmp.put("postId", perDis.getDisObjID());
				tmp.put("title", perDis.getDisObjTopic());
				tmp.put("postDate", perDis.getDisObjRelTime());
				tmp.put("viewNum", perDis.getDisObjLookNum());
				disAry.add(tmp);
			}
			json = new JSONObject();
			json.put("nowPage", nowPage);
			json.put("totalPages", totalPages);
			json.put("disList", disAry);
			JsonUtil.sendJson(response, json.toString());
			break;
		case 4:	//delete selected discuss
			json = new JSONObject();
			if (disObjDao.delObject(Integer.parseInt(postId))) {
				json.put("msg", "true");
			} else {
				json.put("msg", "true");
			}
			JsonUtil.sendJson(response, json.toString());
			break;
		}
		
		
		
	}

}
