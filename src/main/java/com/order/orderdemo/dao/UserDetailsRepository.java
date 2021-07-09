package com.order.orderdemo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.order.orderdemo.dao.entity.User;

@Repository
public interface UserDetailsRepository extends CrudRepository<User, String> {

	@Query("select e from User e where e.username=:username and e.password=:password")
	public User authenticate(@Param("username") String username, @Param("password") String password);
	
	User findByUsername(String userame);
}
