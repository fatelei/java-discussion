package com.discuss.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.discuss.bean.AnswerBean;
import com.discuss.bean.DisObjBean;
import com.discuss.bean.SecDisBean;
import com.discuss.bean.SesVaBean;
import com.discuss.dao.AnswerDao;
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
		HttpSession session = request.getSession();
		int postId = Integer.parseInt(request.getParameter("postId"));
		int nowPage = -1;
		int userId = -1;
		if (request.getParameter("nowPage") != null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		DisObjectDao disObjDao = new DisObjectDao();
		JSONObject json = null;
		DisObjBean disObj = null;
		switch (rplFunFlag) {
		case 1:	//获得回复
			disObj = disObjDao.queryObjByDetail(postId, nowPage, 10);
			json = new JSONObject();
			JSONObject ansJson = new JSONObject();
			JSONArray tmpAry = new JSONArray();
			if (disObj.getAns() != null) {
				ansJson.put("rplUser", disObj.getAns().getAnsUser().getUserName());
				ansJson.put("rplTime", disObj.getAns().getAnsObjRelTime());
				ansJson.put("rplContent", disObj.getAns().getAnsContent());
			}
			tmpAry.add(ansJson);
			json.put("ans", tmpAry.toString());
			JsonUtil.sendJson(response, json.toString());
			break;
		case 2: //发表回复
			userId = (Integer)session.getAttribute(SesVaBean.UserId);
			AnswerDao ansDao = new AnswerDao();
			String rplCnt = request.getParameter("replyContent");
			AnswerBean ansBean = new AnswerBean(rplCnt, userId, postId);
			json = new JSONObject();
			if (ansDao.addAns(ansBean)) {
				json.put("rplSta", "true");
			} else {
				json.put("rplSta", "false");
			}
			JsonUtil.sendJson(response, json.toString());
			break;
		case 3: //发表附议
			String t = (String)session.getAttribute(SesVaBean.LoginState);
			json = new JSONObject();
			if (t == null) {
				json.put("adcSta", "false");
			} else {
				userId = (Integer)session.getAttribute(SesVaBean.UserId);
				SecDiscussDao secDisDao = new SecDiscussDao();
				String addComment = request.getParameter("additionContent");
				SecDisBean secDisBean = new SecDisBean(addComment, userId, postId);
				if (secDisDao.addSecDisc(secDisBean)) {
					json.put("adcSta", "true");
				} else {
					json.put("adcSta", "false");
				}
			}
			JsonUtil.sendJson(response, json.toString());
			break;
		case 4: //引用附议
			break;
		case 5: //获得附议内容
			disObj = disObjDao.queryObjByDetail(postId, nowPage, 1);
			json = new JSONObject();
			JSONArray sdList = new JSONArray();
			SecDiscussDao secDisDao = new SecDiscussDao();
			int totalPages = secDisDao.countSecDisc(postId);
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
			json.put("nowPage", nowPage);
			json.put("totalPages", totalPages);
			response.setCharacterEncoding("UTF-8");
			JsonUtil.sendJson(response, json.toString());
			break;
		}
	}

}
