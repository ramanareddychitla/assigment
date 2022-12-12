package com.centime.centimeapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.centime.centimeapp.entity.Role;
import com.centime.centimeapp.model.NestedRole;
import com.centime.centimeapp.services.impl.RoleService;
import com.centime.custom.annotation.LogMethodParam;

@RestController
public class CentimeResource {

	@Autowired
	RoleService roleService; 
	
	@GetMapping(value = "/getRole/{id}")
	@LogMethodParam
	public ResponseEntity<Role> findByUserId(@PathVariable Long id){
		return ResponseEntity.ok(roleService.findById(id));
	}
	
	@GetMapping(value = "/getNestedRoles")
	@LogMethodParam
	public ResponseEntity<List<NestedRole>> findAll(){
		return ResponseEntity.ok(roleService.findNestedRoles());
	}
	
	
	
}
