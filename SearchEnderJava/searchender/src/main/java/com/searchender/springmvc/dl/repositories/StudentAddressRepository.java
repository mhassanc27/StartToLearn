package com.searchender.springmvc.dl.repositories;

import java.util.List;

import com.searchender.springmvc.pl.entities.StudentAddressEntity;

public interface StudentAddressRepository {
	
	public int createStudentAddress(StudentAddressEntity studentAddressEntity);
	
	public List<StudentAddressEntity> readStudentAddressById(int studentId);
	
	public StudentAddressEntity updateStudentAddress(StudentAddressEntity studentAddressEntity);
	
	public void deleteStudentAddress(StudentAddressEntity studentAddressEntity);

}
