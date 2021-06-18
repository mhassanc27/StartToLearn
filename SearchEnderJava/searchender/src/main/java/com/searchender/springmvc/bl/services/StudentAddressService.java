package com.searchender.springmvc.bl.services;

import java.util.List;

import com.searchender.springmvc.pl.entities.StudentAddressEntity;


public interface StudentAddressService {
	
	public int createStudentAddress(StudentAddressEntity studentAddressEntity);
	
	public List<StudentAddressEntity> readStudentAddressByStudentId(int studentId);
	
	public List<StudentAddressEntity> readStudentAddressById(int id);
	
	public StudentAddressEntity updateStudentAddress(StudentAddressEntity studentAddressEntity);
	
	public void deleteStudentAddress(StudentAddressEntity studentAddressEntity);
	
	

}
