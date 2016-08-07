package com.example.app.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OctopusProperties {

	public OctopusProperties(){
		
	}
	public OctopusProperties(Long oid, String key, String value, String drcvalue) {
		super();
		this.oid = oid;
		this.key = key;
		this.value = value;
		this.drcvalue = drcvalue;
	}
	@Id
	@GeneratedValue
	private Long oid;
	
	private String key;
	private String value;
	private String drcvalue;
	
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDrcvalue() {
		return drcvalue;
	}
	public void setDrcvalue(String drcvalue) {
		this.drcvalue = drcvalue;
	}
	
}
