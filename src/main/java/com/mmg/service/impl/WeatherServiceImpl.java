package com.mmg.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.mmg.common.HttpUtils;
import com.mmg.entity.common.Weather;
import com.mmg.service.WeatherService;
import com.mmg.util.CommonUtil;
import com.mmg.util.JsonUtil;

@Service("weatherService")
public class WeatherServiceImpl implements WeatherService {
	private final String host = "http://saweather.market.alicloudapi.com";
	private final String appcode = "eb7d6123e1164338b29baeb58dfc15e5";
	private final String method = "GET";
	private Log logger = LogFactory.getLog(WeatherServiceImpl.class);

	@SuppressWarnings("unused")
	public static void main(String[] args) {
//		String info = new WeatherServiceImpl().getWeekInfoByIp("101.95.157.134");
//		Map<?,?> map = JsonUtil.jsonToMap(info);
//		Map<?,?> body = (Map<?,?>) map.get("showapi_res_body");
//		Map<?,?> f1 = (Map<?,?>) body.get("f1");
//		String nightTemperature = (String) f1.get("night_air_temperature");
//		String dayTemperature = (String) f1.get("day_air_temperature");
//		String pic = (String )f1.get("day_weather_pic");
		
//		Weather  weather = JsonUtil.jsonToObj(info, Weather.class);
//		System.out.println(weather.getShowapi_res_body().getTime());
		
		new WeatherServiceImpl().get24HourInfo("上海");
	}

	@Override
	public String getWeekInfoByIp(String ip) {
		String path = "/ip-to-weather";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("ip", ip);
		querys.put("needMoreDay", "0");
		querys.put("needAlarm", "0");
	    querys.put("needHourData", "0");
	    querys.put("needIndex", "0");
		try {
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	return EntityUtils.toString(response.getEntity());
	    } catch (Exception e) {
	    	logger.error(e.getMessage());
	    }
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Weather get24HourInfo(String area) {
	    String path = "/hour24";
	    Map<String, String> headers = new HashMap<String, String>();
	    headers.put("Authorization", "APPCODE " + appcode);
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("area", area);

	    try {
	    	Weather weather = new Weather();
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	String info = EntityUtils.toString(response.getEntity());
	    	if(StringUtils.isBlank(info)) return null;
	    	info = info.substring(info.lastIndexOf("showapi_res_body")+18, info.length()-1);
	    	Map<String,Object> map = JsonUtil.jsonToMap(info);
	    	CommonUtil.mapToBean(map, weather);
	    	return weather;
	    } catch (Exception e) {
	    	logger.error(e.getMessage());
	    	return null;
	    }
	}
}
