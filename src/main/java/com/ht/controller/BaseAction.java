package com.ht.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.ht.service.UserService;

public class BaseAction {
	@Autowired
	UserService userService;
}
