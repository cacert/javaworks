package com.example.app.jpa;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Controller {
	
	@Autowired
	private OctopusPropertiesRepository contactsRepository;
	
	@PostConstruct
	public void init(){
		Map<String,String> properties = new ConcurrentHashMap<>(); 
		contactsRepository.findAll().forEach(entry->properties.put(entry.getKey(), entry.getValue()));
		for (Iterator<String> iterator = properties.keySet().iterator(); iterator.hasNext();) {
			String type =  iterator.next();
			System.err.println(type);
		}
	}
	
}