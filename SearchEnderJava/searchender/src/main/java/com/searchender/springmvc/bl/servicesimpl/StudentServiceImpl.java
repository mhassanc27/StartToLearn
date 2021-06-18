package com.searchender.springmvc.bl.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searchender.springmvc.bl.services.StudentService;
import com.searchender.springmvc.dl.repositories.StudentRepository;
import com.searchender.springmvc.pl.entities.StudentEntity;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	public int createStudent(StudentEntity studentEntity) throws Exception{
		
		return studentRepository.createStudent(studentEntity);
	}

	public StudentEntity updateStudent(StudentEntity studentEntity) {
		
		studentRepository.updateStudent(studentEntity);
		return studentEntity;
	}

	public void deleteStudent(StudentEntity studentEntity){
		
		studentRepository.deleteStudent(studentEntity);
	}

	public void deleteAllStudent(List<StudentEntity> studentEntityList) {
		
		studentRepository.deleteAllStudent(studentEntityList);
		
	}
}
