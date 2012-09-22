package com.discuss.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class JsonUtil {
	
	//tranfer map to json
	@SuppressWarnings("rawtypes")
	public static String mapToJson(Map map){
		return JSONObject.fromObject(map).toString();
	}
	
	//send json
	public static void sendJson(HttpServletResponse response, String json){
        PrintWriter out = null;
        response.setCharacterEncoding("utf-8");
		try {
			out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//following	json send map
	@SuppressWarnings("rawtypes")
	public static void sendMap(HttpServletResponse response, Map map){
		sendJson(response, mapToJson(map));
	}
}
