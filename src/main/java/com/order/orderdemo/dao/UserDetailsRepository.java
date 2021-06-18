package com.order.orderdemo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.order.orderdemo.dao.entity.UserDetails;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetails, String> {

	@Query("select e from UserDetails e where e.userName=:userName and e.password=:password")
	public UserDetails authenticate(@Param("userName") String userName, @Param("password") String password);
}
