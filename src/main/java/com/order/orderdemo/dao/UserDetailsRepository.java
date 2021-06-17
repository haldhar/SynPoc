package com.order.orderdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.order.orderdemo.dao.entity.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {

	public UserDetails authenticate(@Param("userName") String contractName, @Param("password") String password);

}
