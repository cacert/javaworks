package com.test.multipledatasource.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages="com.test.multipledatasource.beanb", 
	entityManagerFactoryRef="entityManagerTwo", 
	transactionManagerRef="mytm2")
public class BeanBConfiguration {

	@Bean
	@ConfigurationProperties(prefix="ds.two")
	public DataSource myDS2(){
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerTwo(EntityManagerFactoryBuilder builder){
		return builder.
				dataSource(myDS2())
				.packages("com.test.multipledatasource.beanb")
				.persistenceUnit("two")
				.build();
	}
	
	@Bean
	public JpaTransactionManager mytm2(@Qualifier("entityManagerTwo")EntityManagerFactory factory){
		return new JpaTransactionManager(factory);
	}
}
