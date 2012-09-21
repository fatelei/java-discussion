package com.discuss.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.discuss.bean.DisObjBean;
import com.discuss.bean.SecDisBean;
import com.discuss.bean.SesVaBean;
import com.discuss.dao.DisObjectDao;
import com.discuss.dao.SecDiscussDao;
import com.discuss.util.JsonUtil;

/**
 * Servlet implementation class RplObjServlet
 */
public class RplObjServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RplObjServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int rplFunFlag = Integer.parseInt(request.getParameter("rplFunFlag"));
		int postId = Integer.parseInt(request.getParameter("postId"));
		DisObjectDao disObjDao = new DisObjectDao();
		JSONObject json = null;
		switch (rplFunFlag) {
		case 1:	//获得回复列表
			int nowPage = Integer.parseInt(request.getParameter("nowPage"));
			DisObjBean disObj = disObjDao.queryObjByDetail(postId, nowPage, 10);
			json = new JSONObject();
			JSONObject ansJson = new JSONObject();
			if (disObj.getAns() != null) {
				ansJson.put("rplUser", disObj.getAns().getAnsUser().getUserName());
				ansJson.put("rplTime", disObj.getAns().getAnsObjRelTime());
				ansJson.put("rplContent", disObj.getAns().getAnsContent());
			}
			json.put("ans", ansJson.toString());
			JSONArray sdList = new JSONArray();
			for (SecDisBean sd: disObj.getSecDisList()) {
				JSONObject tmp = new JSONObject();
				tmp.put("secId", sd.getSecDisId());
				tmp.put("secUser", sd.getSecDisUser().getUserName());
				tmp.put("secTime", sd.getSecDisRelTime());
				tmp.put("secContent", sd.getSecDisContent());
				tmp.put("secOppNum", sd.getSecDisOppNum());
				tmp.put("secAprNum", sd.getSecDisSupNum());
				sdList.add(tmp);
			}
			json.put("secList", sdList.toString());
			JsonUtil.sendJson(response, json.toString());
			break;
		case 2: //发表回复
			break;
		case 3: //发表附议
			SecDiscussDao secDisDao = new SecDiscussDao();
			String rplContent = request.getParameter("replyContent");
			int userId = (Integer)request.getSession().getAttribute(SesVaBean.UserId);
			SecDisBean secDisBean = new SecDisBean(rplContent, userId, postId);
			json = new JSONObject();
			if (secDisDao.addSecDisc(secDisBean)) {
				json.put("rplSta", "true");
			} else {
				json.put("rplSta", "false");
			}
			JsonUtil.sendJson(response, json.toString());
			break;
		case 4: //引用附议
			break;
		}
	}

}
