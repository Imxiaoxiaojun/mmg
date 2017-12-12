package com.mmg.service.mmg.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.mmg.common.HttpUtils;
import com.mmg.vo.IpAddress;
import com.mmg.service.mmg.IpService;
import com.mmg.util.JsonUtil;

@Service("ipService")
public class IpServiceImpl implements IpService{
	private final String host = "http://saip.market.alicloudapi.com";
	private final String method = "GET";
	private final String appcode = "eb7d6123e1164338b29baeb58dfc15e5";
	private final  Log logger = LogFactory.getLog(IpServiceImpl.class);
	@Override
	public IpAddress queryLocalByIp(String ip) {
	    String path = "/ip";
	    Map<String, String> headers = new HashMap<String, String>();
	    headers.put("Authorization", "APPCODE " + appcode);
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("ip", ip);
	    
	    try {
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	IpAddress ipAddress = new IpAddress();
	    	String info = EntityUtils.toString(response.getEntity());
	    	if(StringUtils.isBlank(info)) return null;
	    	info = info.substring(info.lastIndexOf("showapi_res_body")+18, info.length()-1);
	    	ipAddress = JsonUtil.jsonToObj(info, IpAddress.class);
	    	if(null == ipAddress) return null;
	    	return ipAddress;
	    	
	    } catch (Exception e) {
	    	logger.error(e.getMessage());
	    	return null;
	    }
	}
	public static void main(String[] args) {
		new IpServiceImpl().queryLocalByIp("101.95.157.134");
	}
}
