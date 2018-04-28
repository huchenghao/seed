package com.ht.config;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @ClassName: GlobalExceptionHandler
 * @Description: 统一异常捕获;
 * 					log记录异常详细信息;
 * 					json返回异常message
 * @author: huchenghao
 * @date: 2018年4月28日 上午11:09:41
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public MessageInfo jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		StringWriter sw = new StringWriter();   
		if(e instanceof IllegalArgumentException){//参数校验异常
			logger.warn("{method:"+req.getRequestURI()+"}-->["+e.getMessage()+"]");
		}else{
			e.printStackTrace(new PrintWriter(sw, true));   
	        logger.error(sw.toString());
		}
	    return ResultGenerator.genFailResult(e.getMessage());
	}


}
