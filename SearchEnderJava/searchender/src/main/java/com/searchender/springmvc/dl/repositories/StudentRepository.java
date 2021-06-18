package com.searchender.springmvc.dl.repositories;


import java.util.List;

import com.searchender.springmvc.pl.entities.StudentEntity;

public interface StudentRepository{
	
	public int createStudent(StudentEntity studentEntity);
	
	
	public int readStudent();
	
	public StudentEntity updateStudent(StudentEntity studentEntity);
	
	public void deleteStudent(StudentEntity studentEntity);
	
	public void deleteAllStudent(List<StudentEntity> studentEntityList);

}
