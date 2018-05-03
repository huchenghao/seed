package com.ht.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;


@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
	private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);
	
    @Value("${spring.profiles.active}")
    private String env;
    
  //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	if (!"dev".equals(env)){ //开发环境忽略签名认证   
            registry.addInterceptor(new HandlerInterceptorAdapter() {
                @Override
                public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                	if(request.getRequestURI().contains("/v1x/")){//app接口;需要加签验签
                     	if(validateSign(request)){
                             return true;
                        }else{
                             logger.warn("签名认证失败，请求接口：{}，请求IP：{}，请求参数：{}",
                                     request.getRequestURI(), getIpAddress(request), JSON.toJSONString(request.getParameterMap()));
                             MessageInfo result = new MessageInfo();
                             result.setCode(MessageCode.unauthorized);
                             result.setMsg("签名认证失败");
                             responseResult(response, result);
                             return false;
                        }
                	}else{
                		return true;
                	}
                }
            });
        }
    }
    
    private void responseResult(HttpServletResponse response, MessageInfo result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
       
    }
    
    /**
     * 一个简单的签名认证，规则：
     * 1. 将请求参数按ascii码排序
     * 2. 拼接为a=value&b=value...这样的字符串（不包含sign）
     * 3. 混合密钥（secret）进行md5获得签名，与请求的签名进行比较
     */
    private boolean validateSign(HttpServletRequest request) {
    	List<String> keys = new ArrayList<String>(request.getParameterMap().keySet());
    	keys.remove("from");
        keys.remove("isappinstalled");
        keys.remove("nsukey");
    	if(keys == null || keys.size() ==0){
    		return true;
    	}
        String requestSign = request.getParameter("sign");//获得请求签名，如sign=19e907700db7ad91318424a97c54ed57
        if (StringUtils.isEmpty(requestSign)) {
            return false;
        }
        keys.remove("sign");//排除sign参数
        //微信分享会附带一些参数,分享出去的链接会带from、isappinstalled、nsukey;
        
        Collections.sort(keys);//排序
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key).append("=").append(request.getParameter(key)).append("&");//拼接字符串
        }
        String linkString = sb.toString();
        linkString = StringUtils.substring(linkString, 0, linkString.length() - 1);//去除最后一个'&'
        String secret = "HuaTengEnterpriseOA";//密钥
        String sign = DigestUtils.md5Hex(linkString + secret);//混合密钥md5
        return StringUtils.equals(sign, requestSign);//比较
    }
    
    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果是多级代理，那么取第一个ip为客户端ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(0, ip.indexOf(",")).trim();
        }
        return ip;
    }
}
