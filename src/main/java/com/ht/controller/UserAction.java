package com.ht.controller;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ht.config.MessageInfo;
import com.ht.config.StringText;

@RestController
@RequestMapping("/user")
public class UserAction extends BaseAction{
	
	@RequestMapping("/login")
	public MessageInfo login(String mobile ,String password){
		Assert.hasText(mobile, StringText.app.mobile_required);
		Assert.hasText(password, StringText.app.password_required);
		return userService.login(mobile, password);
	}
}
