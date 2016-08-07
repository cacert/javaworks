package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import com.example.app.jpa.Appconfig;
import com.example.app.jpa.OctopusProperties;
import com.example.app.jpa.OctopusPropertiesRepository;
import com.example.app.jpa.SettingService;

@SpringBootApplication
public class DemoApplication  implements CommandLineRunner{

	@Autowired
	OctopusPropertiesRepository repo ;
	@Autowired
	SettingService service ;
	@Autowired
	Appconfig appconfig ;
	@Autowired
	Environment env ;
	
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		insertData();
		appconfig.propertySourcesPlaceholderConfigurer(repo);
		System.err.println(service.getValue());
		System.err.println(env.getProperty("key1"));
		System.err.println(env.getProperty("key2"));
		System.err.println(env.getProperty("key3"));
		
	}

	private void insertData() {
		repo.save(new OctopusProperties(1L,"key1","dbvalue1","drcdbvalue1"));
		repo.save(new OctopusProperties(1L,"key2","dbvalue2","drcdbvalue2"));
		repo.save(new OctopusProperties(1L,"key3","dbvalue3","drcdbvalue3"));
	}
	

}
