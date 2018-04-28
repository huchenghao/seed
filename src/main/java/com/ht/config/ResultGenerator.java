package com.ht.config;


/**
 * 
 * @ClassName: ResultGenerator
 * @Description: 响应结果生成工具类
 * @author: huchenghao
 * @date: 2017年8月22日 上午9:54:53
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    
    public static MessageInfo genSuccessResult() {
    	MessageInfo info = new MessageInfo();
    	info.setCode("0");
    	info.setMsg(DEFAULT_SUCCESS_MESSAGE);
        return info;
    }

    public static MessageInfo genSuccessResult(Object data) {
    	MessageInfo info = new MessageInfo();
    	info.setCode("0");
    	info.setMsg(DEFAULT_SUCCESS_MESSAGE);
    	info.setResult(data);
        return info;
    }

    public static MessageInfo genFailResult(String message) {
    	MessageInfo info = new MessageInfo();
    	info.setCode("2");
    	info.setMsg(message);
        return info;
    }
    
    public static MessageInfo gen500FailResult(String message) {
    	MessageInfo info = new MessageInfo();
    	info.setCode("1");
    	info.setMsg(message);
        return info;
    }
}
