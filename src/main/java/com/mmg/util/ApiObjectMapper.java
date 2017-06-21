package com.mmg.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ApiObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 2L;

	public ApiObjectMapper() {
		this.setup();
	}

	private void setup() {
		this.disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
		this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

}