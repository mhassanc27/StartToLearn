package com.searchender.springmvc.dl.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.searchender.springmvc.pl.entities.StudentAddressEntity;

@Repository
public interface StudentAddressJpaRepository extends JpaRepository<StudentAddressEntity, Integer>{
	
	public List<StudentAddressEntity> findByStudentId(int studentId);

}
