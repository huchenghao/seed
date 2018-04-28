package com.ht.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController//针对APP的接口,此控制层返回的全部是JSON数据
@RequestMapping("/hello")
public class HelloWorldAction {
	
	@RequestMapping("/h")
	public String hello(){
		System.out.println("into h");
		return "SUC";
	}
}
