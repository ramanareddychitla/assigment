package com.centime.centimeapp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NestedRole {
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<NestedRole> getSubClasses() {
		return subClasses;
	}

	public void setSubClasses(List<NestedRole> subClasses) {
		this.subClasses = subClasses;
	}

	@JsonProperty("Name")
	private String name;
	
	@JsonProperty("Sub Classes")
	private List<NestedRole> subClasses;

}
