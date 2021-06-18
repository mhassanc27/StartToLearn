package com.searchender.springmvc.bl.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searchender.springmvc.bl.services.StudentLoginService;
import com.searchender.springmvc.dl.repositories.StudentLoginRepository;
import com.searchender.springmvc.pl.entities.StudentEntity;

@Service
public class StudentLoginServiceImpl implements StudentLoginService {
	
	@Autowired
	private StudentLoginRepository studentLoginRepository;

	public List<StudentEntity> findByUserNameAndUserPassword(String userName, String userPassword) {
				
		return studentLoginRepository.findByUserNameAndUserPassword(userName, userPassword);
	}

}
