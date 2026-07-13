package com.kh.spring11.configuration;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

//"" =>널로
public class EmptyStringToNullDeserializer extends JsonDeserializer<String> {

	@Override
	public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		String value = p.getText();
		//원래 널이거나 공백을 제거해보니 비어있는 경우는 널로
		if(value == null || value.strip().isEmpty())
			return null;
		
		return null;
	}

}
