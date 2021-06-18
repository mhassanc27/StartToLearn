package com.searchender.springmvc.dl.repositoriesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.searchender.springmvc.dl.repositories.StudentAddressRepository;
import com.searchender.springmvc.pl.entities.StudentAddressEntity;

@Repository
public class StudentAddressRepositoryImpl implements StudentAddressRepository {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Transactional
	public int createStudentAddress(StudentAddressEntity studentAddressEntity) {
						
		return (Integer) this.hibernateTemplate.save(studentAddressEntity);
	}

	public List<StudentAddressEntity> readStudentAddressById(int id) {
		
		this.hibernateTemplate.get(StudentAddressEntity.class, id);
		
		return null;
	}

	@Transactional
	public StudentAddressEntity updateStudentAddress(StudentAddressEntity studentAddressEntity) {
		
		this.hibernateTemplate.update(studentAddressEntity);
		return studentAddressEntity;
	}

	@Transactional
	public void deleteStudentAddress(StudentAddressEntity studentAddressEntity) {
		
		this.hibernateTemplate.delete(studentAddressEntity);
		
	}

}
