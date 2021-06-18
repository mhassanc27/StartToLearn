package com.searchender.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.searchender.Model.Test;
import com.searchender.dao.TestDao;

@Repository
public class TestDaoImpl implements TestDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Transactional
	public int insertTest(Test test) {
		int id=(Integer)this.hibernateTemplate.save(test);
		return id;
	}

}
