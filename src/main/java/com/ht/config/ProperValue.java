package com.ht.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class ProperValue {
	/**
	 * rongyunappkey
	 */
	public static String rongyun_appKey;   
	
	@Value("${rongyun.appKey}") 
	public  void setRongyun_appKey(String rongyun_appKey) {
		ProperValue.rongyun_appKey = rongyun_appKey;
	}
	/**
	 * rongyunappsecret
	 */
	public static String rongyun_appSecret;
	
	@Value("${rongyun.appSecret}") 
	public  void setRongyun_appSecret(String rongyun_appSecret) {
		ProperValue.rongyun_appSecret = rongyun_appSecret;
	}
	
	public static String baseUrl;
	
	@Value("${base.url}") 
	public  void setBaseUrl(String baseUrl) {
		ProperValue.baseUrl = baseUrl;
	}
	
	public static String picHttp;

	@Value("${base.picUrl}") 
	public  void setPicHttp(String picHttp) {
		ProperValue.picHttp = picHttp;
	}
	
	public static boolean jpush_ios_mode;
	
	@Value("${jpush.ios.mode}") 
	public  void setJpush_ios_mode(boolean jpush_ios_mode) {
		ProperValue.jpush_ios_mode = jpush_ios_mode;
	}
	
	public static String spring_profiles_active;
	
	@Value("${spring.profiles.active}") 
	public  void setSpring_profiles_active(String spring_profiles_active) {
		ProperValue.spring_profiles_active = spring_profiles_active;
	}
	
}
