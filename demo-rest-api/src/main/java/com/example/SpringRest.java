package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringRest {
	@RequestMapping(value = "/rs/hello")
	public String index() {
		return "Hello from REST!";
	}
}
