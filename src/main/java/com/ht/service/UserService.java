package com.ht.service;

import com.ht.config.MessageInfo;

public interface UserService {
	
	MessageInfo login(String mobile,String password);
}
