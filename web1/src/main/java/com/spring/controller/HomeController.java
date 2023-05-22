package com.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller // http://localhost:8080 ��û ���� ��Ʈ�ѷ�
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * value : �ش� ��Ʈ�ѷ��� � ��û�� ó���� ������ �ۼ�
	 * 
	 * http://localhost:8080/ +GET
	 */
	//@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("/")
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		return "index"; //=> ViewResolver : WEB-INF/views/home.jsp
	}
	//@RequestMapping(value = "/doA", method = RequestMethod.GET)
	@GetMapping("/doA")
	public String doA() {
		logger.info("doA...");
		//result.jsp �����ְ��ʹٸ�?
		return "result";
	}


	
}