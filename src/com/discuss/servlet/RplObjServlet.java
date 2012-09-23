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
import com.discuss.util.StrUtil;
public class RplObjServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rplFunFlag = Integer.parseInt(StrUtil.tranISOToUTF(request.getParameter("rplFunFlag")));
		HttpSession session = request.getSession();
		int postId = -1;
		if (StrUtil.tranISOToUTF(request.getParameter("postId")) != null) {
			postId = Integer.parseInt(StrUtil.tranISOToUTF(request.getParameter("postId")));
		}
		int nowPage = -1;
		int userId = -1;
		int secId = -1;
		int total = 0;
		if (StrUtil.tranISOToUTF(request.getParameter("nowPage")) != null) {
			nowPage = Integer.parseInt(StrUtil.tranISOToUTF(request.getParameter("nowPage")));
		}
		DisObjectDao disObjDao = new DisObjectDao();
		JSONObject json = null;
		DisObjBean disObj = null;
		SecDiscussDao secDisDao = null;
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
				tmpAry.add(ansJson);
			}
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
				json.put("errmsg", "请先登录");
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
				json.put("errmsg", "请先登录");
			} else {
				userId = (Integer)session.getAttribute(SesVaBean.UserId);
				secDisDao = new SecDiscussDao();
				String addComment = request.getParameter("additionContent");
				System.out.println(addComment);
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
			secDisDao = new SecDiscussDao();
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
			JsonUtil.sendJson(response, json.toString());
			break;
		case 6: //更新支持
			secId = Integer.parseInt(request.getParameter("secId"));
			userId = (Integer)session.getAttribute(SesVaBean.UserId);
			
			secDisDao = new SecDiscussDao();
			json = new JSONObject();
			
			//get ip add
			String ipAdd = request.getRemoteAddr(); 
			total = secDisDao.updateTheSuptNum(secId, 1, ipAdd, userId, secId);
			if (total != -1) {
				json.put("upSta", "true");
				json.put("upNum", total);
			} else if (total == -2) {
				json.put("upSta", "false");
				json.put("errmsg", "您已经投过票了!");
			} else {
				json.put("upSta", "false");
			}
			JsonUtil.sendJson(response, json.toString());
			break;
		case 7: //更新反对
			secId = Integer.parseInt(request.getParameter("secId"));
			userId = (Integer)session.getAttribute(SesVaBean.UserId);
			
			secDisDao = new SecDiscussDao();
			json = new JSONObject();
			//get ip add
			String ipAddr = request.getRemoteAddr(); 
			
			total = secDisDao.updateTheOppNum(secId, 1, ipAddr, userId, secId);
			if (total != -1) {
				json.put("upSta", "true");
				json.put("opNum", total);
			} else {
				json.put("upSta", "false");
			}
			JsonUtil.sendJson(response, json.toString());
			break;
		}
	}

}
