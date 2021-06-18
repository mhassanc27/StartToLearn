package com.searchender.springmvc.dl.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.searchender.springmvc.pl.entities.StudentEntity;

@Repository
public interface StudentLoginRepository extends CrudRepository<StudentEntity, Integer>{

	public List<StudentEntity> findByUserNameAndUserPassword(String userName,String userPassword);
}
