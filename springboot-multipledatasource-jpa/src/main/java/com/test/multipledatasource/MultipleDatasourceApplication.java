package com.test.multipledatasource;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySources;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.test.multipledatasource.beana.BeanA;
import com.test.multipledatasource.beana.BeanAJPARepository;
import com.test.multipledatasource.beanb.BeanB;

@SpringBootApplication
@EnableTransactionManagement
public class MultipleDatasourceApplication implements CommandLineRunner{

	@Autowired 
	BeanAJPARepository beana;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(MultipleDatasourceApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
	}
}
