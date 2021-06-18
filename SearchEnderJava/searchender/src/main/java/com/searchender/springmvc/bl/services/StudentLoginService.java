package com.searchender.springmvc.bl.services;

import java.util.List;

import com.searchender.springmvc.pl.entities.StudentEntity;

public interface StudentLoginService {
	
	public List<StudentEntity> findByUserNameAndUserPassword(String userName,String userPassword);

}
