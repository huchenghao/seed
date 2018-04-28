package com.ht.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ht.dao.UserDao;

public class BaseServiceImpl {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	UserDao userDao;
}
