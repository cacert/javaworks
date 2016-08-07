package com.example.app.jpa;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;


@Component
public class Appconfig {

	@Autowired
	PropertySourcesPlaceholderConfigurer property ;
	
	@Autowired
    private ConfigurableEnvironment env;
	
	public void propertySourcesPlaceholderConfigurer(OctopusPropertiesRepository repo) {
		
		Map<String,Object> props = new ConcurrentHashMap<>();
		List<OctopusProperties> loadedSettings = repo.findAll();
		loadedSettings.forEach(entry -> props.put(entry.getKey(), entry.getValue()));
		MutablePropertySources mutablePropertySources = env.getPropertySources();
		mutablePropertySources.addLast(new MapPropertySource("custom", props));
	
	}
}

