package com.searchender.springmvc.dl.repositoriesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.searchender.springmvc.dl.repositories.StudentRepository;
import com.searchender.springmvc.pl.entities.StudentEntity;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Transactional
	public int createStudent(StudentEntity studentEntity) {
		
		return (Integer)this.hibernateTemplate.save(studentEntity);
	}

	public int readStudent() {
		
		
		
		return 0;
	}

	@Transactional
	public StudentEntity updateStudent(StudentEntity studentEntity) {
		
		this.hibernateTemplate.update(studentEntity);
		return studentEntity;
	}
	@Transactional
	public void deleteStudent(StudentEntity studentEntity) {
		
		this.hibernateTemplate.delete(studentEntity);
		
	}
	
	@Transactional
	public void deleteAllStudent(List<StudentEntity> studentEntityList){
		this.hibernateTemplate.deleteAll(studentEntityList);
	}

	

}
