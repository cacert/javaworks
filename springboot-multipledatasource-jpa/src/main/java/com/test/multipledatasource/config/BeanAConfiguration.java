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
@EnableJpaRepositories(basePackages="com.test.multipledatasource.beana",
		transactionManagerRef="mytm1",
		entityManagerFactoryRef="entityManagerOne"
		)
public class BeanAConfiguration {


	@ConfigurationProperties(prefix="ds.one")
	@Bean
	@Primary
	public DataSource myDS1(){
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerOne(EntityManagerFactoryBuilder builder){
		return builder.dataSource(myDS1()).packages("com.test.multipledatasource.beana").persistenceUnit("one").build();
	}
	
	@Bean
	@Primary
	public JpaTransactionManager mytm1(@Qualifier("entityManagerOne")EntityManagerFactory factory){
		return new JpaTransactionManager(factory);
	}
}
