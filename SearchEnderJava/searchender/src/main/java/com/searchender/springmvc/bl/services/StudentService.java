package com.searchender.springmvc.bl.services;

import java.util.List;

import com.searchender.springmvc.pl.entities.StudentEntity;

public interface StudentService {
	
	public int createStudent(StudentEntity studentEntity) throws Exception;
	public StudentEntity updateStudent(StudentEntity studentEntity);
	public void deleteStudent(StudentEntity studentEntity);
	public void deleteAllStudent(List<StudentEntity> studentEntityList);

}
