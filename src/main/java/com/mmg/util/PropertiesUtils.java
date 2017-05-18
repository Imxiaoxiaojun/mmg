package com.mmg.util;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

public class PropertiesUtils implements EmbeddedValueResolverAware{
	private static StringValueResolver stringValueResolver;
	private PropertiesUtils(){}
	
	@Override
	public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
		PropertiesUtils.stringValueResolver = stringValueResolver;
	}
	
	public static String getPropertiesValue(String name){
		name = "${" + name + "}";
		return stringValueResolver.resolveStringValue(name);
	}
}
