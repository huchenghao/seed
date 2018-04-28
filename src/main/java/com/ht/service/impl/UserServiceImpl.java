package com.ht.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.ht.config.MessageInfo;
import com.ht.config.ResultGenerator;
import com.ht.config.StringText;
import com.ht.model.User;
import com.ht.service.UserService;
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService{

	@Override
	public MessageInfo login(String mobile,String password) {
		User user = userDao.findByMobile(mobile);
		if(user == null){
			return ResultGenerator.genFailResult(StringText.app.mobile_un_reg);
		}
		if(!StringUtils.equals(password, user.getPassword())){
			return ResultGenerator.genFailResult(StringText.app.password_error);
		}
		
		return ResultGenerator.genSuccessResult(user);
	}

}
