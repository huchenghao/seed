package com.ht.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ht.dao.conf.RedisRepository;


@RestController
@RequestMapping("/redis")
public class RedisAction {
	@Autowired
	RedisRepository<String, Object> redis;
	
	@Cacheable(value="redis_t2",key="#useruuid")
	@RequestMapping("/t2")
	public String t_2(String useruuid){
		
		return "SUC";
	}
	
	
	@RequestMapping("/t")
	public void t(){
		redis.set("name", "huchenghao2222");
		String name = (String) redis.get("name");
		System.out.println(name);
		
	}
	
}
