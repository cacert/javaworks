package com.example.app.jpa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SettingService {
	

	@Value("${key1}")
	private String value;
	
	public String getValue() {
		return value;
	}
}
