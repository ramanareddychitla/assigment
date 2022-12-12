package com.centime.centimeapp.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centime.centimeapp.entity.Role;
import com.centime.centimeapp.jpa.RoleRepository;
import com.centime.centimeapp.model.NestedRole;
import com.centime.custom.annotation.LogMethodParam;

@Service
public class RoleService {
	
	private static final Logger LOG = LoggerFactory.getLogger(RoleService.class);

	
	@Autowired
	RoleRepository roleRepository;
	
	@LogMethodParam
	public Role findById(Long id){
		LOG.info("Id is" + id);
		return roleRepository.findById(id).get();
	}

	public List<Role> findAll()    {
        return (List<Role>)roleRepository.findAll();
    }
	
	
	public List<NestedRole> findNestedRoles(){
		
		List<Role> allRoles = roleRepository.findAll();
		Map<Long, Role> idVsRole = allRoles.stream().collect(Collectors.toMap(Role::getId ,Function.identity()));
		ConcurrentHashMap<Integer, List<Role>> groupedByParent =  (ConcurrentHashMap<Integer, List<Role>>) allRoles.stream().collect(Collectors.groupingByConcurrent(Role::getParentid));	
		
		List<NestedRole> nestedRoles = new ArrayList<>();
		for (Integer key : groupedByParent.keySet()) {
			if(key == 0) {
				continue;
			}
			
			List<Role> value = groupedByParent.get(key);
			if(value == null)
				continue;
			
			NestedRole nestedRole = new NestedRole();
			nestedRole.setName(idVsRole.get(new Long(key)).getName());
			List<NestedRole> auxilaryRoles = new ArrayList<>();
			fillDTO(groupedByParent, value, auxilaryRoles);
			nestedRole.setSubClasses(auxilaryRoles);
			nestedRoles.add(nestedRole);
			}

		return nestedRoles;
	}
	
	private static void fillDTO(ConcurrentHashMap<Integer, List<Role>> groupedByParent, List<Role> roles, List<NestedRole> nestedRoles) {
		for (int i = 0; i < roles.size(); i++) {
			NestedRole nestedRole = new NestedRole();
			nestedRole.setName(roles.get(i).getName());
			if(groupedByParent.get(roles.get(i).getId().intValue())!=null) {
				List<NestedRole> auxNestedRoles = new ArrayList<>();
				fillDTO(groupedByParent, groupedByParent.get(roles.get(i).getId().intValue()), auxNestedRoles);
				nestedRole.setSubClasses(auxNestedRoles);
				groupedByParent.remove(roles.get(i).getId().intValue());
			}
			nestedRoles.add(nestedRole);
		}
		
		
	}
}
