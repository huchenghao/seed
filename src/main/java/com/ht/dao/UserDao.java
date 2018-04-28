package com.ht.dao;

import com.ht.dao.conf.BaseDao;
import com.ht.model.User;

public interface UserDao extends BaseDao<User, Long>{
	
	User findByMobile(String mobile);
}
