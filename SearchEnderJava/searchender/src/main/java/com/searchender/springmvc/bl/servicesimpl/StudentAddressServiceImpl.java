package com.searchender.springmvc.bl.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searchender.springmvc.bl.services.StudentAddressService;
import com.searchender.springmvc.dl.repositories.StudentAddressJpaRepository;
import com.searchender.springmvc.dl.repositories.StudentAddressRepository;
import com.searchender.springmvc.pl.entities.StudentAddressEntity;

@Service
public class StudentAddressServiceImpl implements StudentAddressService {
	
	@Autowired
	private StudentAddressRepository studentAddressRepository;
	
	@Autowired
	private StudentAddressJpaRepository studentAddressJpaRepository;

	public int createStudentAddress(StudentAddressEntity studentAddressEntity) {
				
		return studentAddressRepository.createStudentAddress(studentAddressEntity);
	}
	
	public List<StudentAddressEntity> readStudentAddressByStudentId(int studentId) {
		
		return studentAddressJpaRepository.findByStudentId(studentId);
	}

	public List<StudentAddressEntity> readStudentAddressById(int studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	public StudentAddressEntity updateStudentAddress(StudentAddressEntity studentAddressEntity) {
		
		return studentAddressRepository.updateStudentAddress(studentAddressEntity);
	}

	public void deleteStudentAddress(StudentAddressEntity studentAddressEntity) {
		studentAddressRepository.deleteStudentAddress(studentAddressEntity);
		
	}

	

}
