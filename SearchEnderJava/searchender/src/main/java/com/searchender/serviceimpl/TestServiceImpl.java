package com.searchender.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searchender.Model.Test;
import com.searchender.dao.TestDao;
import com.searchender.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	
	@Autowired
	private TestDao testDao;

	public int saveTest(Test test) {
		int id=testDao.insertTest(test);
		return id;
	}
	
	

}
