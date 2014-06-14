package com.shop.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSON;

/**
 * json转换工具类
 * @author caryCheng
 */
public class JsonUtils {
	public static String getHttpBody(HttpServletRequest request) throws IOException
	{	
		InputStreamReader isr = new InputStreamReader(request.getInputStream(), "utf-8");
		BufferedReader reader=new BufferedReader(isr);
		StringBuffer sb=new StringBuffer();
//		BufferedReader reader = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream())); 
		String str=null;
		while(true)
		{
			str=reader.readLine();
			if(str!=null)
			{
				sb.append(str);
			}
			else
			{
				break;
			}
		}
		return sb.toString();
	}
	public static void main(String[] args) {
//		Model1 m1=new Model1("小样", 18);
//		try {
//			String jsonStr=objectToJackson(m1, Model1.class);
//			System.out.println(jsonStr);
//			String jsonStr1="{'name':'小样','age':18}";
//			Map<String,Object> m=jsonToMap(jsonStr1);
//			System.out.println(m.get("name"));
//			System.out.println(m.get("age"));
//		} catch (JsonGenerationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	/**
	 * 
	 * @param json
	 * @param cls
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static String objectToJackson(Object json, Class cls)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerSubtypes(cls);
		String reqJson = mapper.writeValueAsString(json);
		return reqJson;
	}

	/**
	 * 
	 * @param json
	 * @param cls
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static Object jsongToObject(String json, Class cls)
			throws JsonGenerationException, JsonMappingException, IOException {
		Object obj = null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		obj = mapper.readValue(json, cls);
		return obj;
	}

	public static Map<String, Object> toMap(String json) throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> map = null;
		ObjectMapper mapper = new ObjectMapper();
		map = mapper.readValue(json, Map.class);
		return map;
	}

	public static String toJson(Map<String, String> map) {
		Set<String> keys = map.keySet();
		String key = "";
		String value = "";
		StringBuffer jsonBuffer = new StringBuffer();
		jsonBuffer.append("{");
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			key = it.next();
			value = map.get(key);
			jsonBuffer.append(key + ":" + value);
			if (it.hasNext()) {
				jsonBuffer.append(",");
			}
		}
		jsonBuffer.append("}");
		return jsonBuffer.toString();
	}

	/**
	 * 
	 * @param json
	 * @param c
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public static Object jsonToObject(String json) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Object object = null;
//		mapper.enableDefaultTyping();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		object = jsongToObject(json, Object.class);

		return object;
	}

	/**
	 * 将json字符串转换为map对象
	 * 
	 * @param url
	 * @param object
	 * @return
	 */
	public static HashMap<String, Object> jsonToMap(String jsonString) {
		HashMap<String, Object> retMap = null;
		if (jsonString == null) {
			return null;
		} else {
			retMap = JSON.parseObject(jsonString, HashMap.class);
		}
		return retMap;
	}

//	public static void main(String[] s) throws JsonGenerationException,
//			JsonMappingException, IOException {
//		// String json="[{\"userId\":'1'},{'userId':'2'}]";
//		// List t5=(List)JascksonUtil.jsonToObject(json);
//		// Map user1=(Map)t5.get(0);
//		// logger.debug("user1:{}",user1.get("userId"));
//		String jsonStr = "{'ATTACHAMOUNT':'0','PRODUCTAMOUNT':'5000','UPTRANSEQ':'20120731661159','RETNCODE':'0000','ORDERAMOUNT':'5000','ORDERSEQ':'600102310100003720120924003383','TRANDATE':'20120731','CURTYPE':'RMB','RETNINFO':'0000','ORDERREQTRANSEQ':'201207311022090000000000888942'}";
//		JascksonUtil.jsonToMap(jsonStr);
//	}
}
