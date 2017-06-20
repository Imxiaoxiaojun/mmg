package com.mmg.util;

import com.google.gson.Gson;
import com.google.gson.JsonNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yj on 2017/5/13.
 */
public class JsonUtil {
    private static Gson gson = new Gson();
    private static ApiObjectMapper mapper = new ApiObjectMapper();
    

    public static void main(String[] args) {
      
    }
    
    public static String objToJson(Object obj){
		try {
			String json = mapper.writeValueAsString(obj);
			return json;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
    public static <T> T jsonToObj(String jsonVal, Class<T> valueType) {
		try{
			T obj = mapper.readValue(jsonVal, valueType);
			return obj;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
    
    /**
     * @param src :将要被转化的对象
     * @return :转化后的JSON串
     * @MethodName : toJson
     * @Description : 将对象转为JSON串，此方法能够满足大部分需求
     */
    public static String toJson(Object src) {
        if (src == null) {
            return gson.toJson(JsonNull.INSTANCE);
        }
        return gson.toJson(src);
    }

    @SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToMap(String jsonVal) {
		try{
			return mapper.readValue(jsonVal, Map.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
