package com.example;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TestConfig {

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		CXFServlet cxfServlet = new CXFServlet();
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(cxfServlet, "/api/*");
		servletRegistrationBean.setLoadOnStartup(1);
		return servletRegistrationBean;
	}
	
	@Bean(name=Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {      
        return new SpringBus();
    }
	
	@Bean
	public org.codehaus.jackson.jaxrs.JacksonJsonProvider jsonProvider(){
		JacksonJsonProvider jsonProvider = new JacksonJsonProvider();
		return jsonProvider;
	}
	@Bean
	public org.apache.cxf.jaxrs.swagger.Swagger2Feature swagger2Feature(){
		Swagger2Feature feature = new org.apache.cxf.jaxrs.swagger.Swagger2Feature();
		feature.setBasePath("/swagger-api");
		return feature;
	}

}

