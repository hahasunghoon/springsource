package com.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
	//@RequestMapping(value="/read", method =RequestMethod.GET)
	@GetMapping("/read")
	public void readGet(HttpServletRequest req) {
		log.info("read 夸没");
	}
	//@RequestMapping(value="/register", method =RequestMethod.GET)
	@GetMapping("/register")
	public void registerGet(HttpServletRequest req) {
		log.info("register 夸没");
	}
	//@RequestMapping(value="/modify", method =RequestMethod.GET)
	@GetMapping("/modify")
	public void modifyGet(HttpServletRequest req) {
		log.info("modify 夸没");
	}
	//@RequestMapping(value="/ remove", method =RequestMethod.GET)
	@GetMapping("/remove")
	public void removeGet(HttpServletRequest req) {
		log.info(" remove夸没");
	}

}
