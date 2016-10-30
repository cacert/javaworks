package com.test.multipledatasource.beanb;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="ITG", name="SERVICEB")
public class BeanB {
	@Id
	private Long id;
	private String name;
	public BeanB(){}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
