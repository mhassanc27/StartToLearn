package com.searchender.dao;

import org.springframework.stereotype.Repository;

import com.searchender.Model.Test;

@Repository
public interface TestDao {
	
	public int insertTest(Test test);
	

}
