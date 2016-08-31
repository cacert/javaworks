package com.example;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TestRouteRestDSL extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet").apiContextPath("/api-doc")
        .apiProperty("api.title", "User API").apiProperty("api.version", "1.2.3").apiContextPath("/camel")
        // and enable CORS
        .apiProperty("cors", "true");
	
		rest("/say/hello")
        .get().route().transform().constant("Hello World");
		
		
		rest("/path/").get("hi").route().id("kasim").transform(constant("kasim"));
	}

}
