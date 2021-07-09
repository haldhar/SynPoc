package com.order.orderdemo.service;

import org.springframework.data.repository.CrudRepository;

import com.order.orderdemo.dao.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	Role findByName(String rolename);
	
	@SuppressWarnings("unchecked")
	Role save(Role userRoles);
}
