package com.ctgu.npc.business.common.utils;

import com.google.gson.Gson;
import net.sf.json.JSONObject;


public class GsonUtils {

	public static String getJsonString(Object obj) {
		
		
		Gson gson = new Gson();
		String gsonStr = gson.toJson(obj);
		return gsonStr;
	}

	public static String getJsonString(String key, Object object) {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put(key, object);
		return jsonObject.toString();
	}

	public GsonUtils() {
		// TODO Auto-generated constructor stub
	}

}
