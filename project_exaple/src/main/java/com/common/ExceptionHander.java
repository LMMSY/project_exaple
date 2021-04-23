package com.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHander {

	private static Logger log = LoggerFactory.getLogger(com.common.ExceptionHander.class);

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public Object handerException(HttpServletRequest req, Exception e) {
		if (e instanceof DataAccessException) {
			log.error("数据库异常", e);
			return new CommonResponse(StatusCode.DATABASE_EXCEPTION);
		} else if (e instanceof NullPointerException) {
			log.error("空指针异常", e);
			return new CommonResponse(StatusCode.SEARCH_NULL);
		}else {
			log.error("", e);
			return new CommonResponse(StatusCode.UNKNOWN_EXCEPTION);
		}
	}
}
