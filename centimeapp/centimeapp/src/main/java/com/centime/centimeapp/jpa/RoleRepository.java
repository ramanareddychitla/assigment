package com.centime.centimeapp.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.centime.centimeapp.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	public List<Role> findAll();
	
	public Optional<Role> findById(Long id);
}
