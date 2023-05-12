package com.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller //http://localhost:8080 ��û ���� ��Ʈ�ѷ�
@RequestMapping("/member")
public class MemberController {
	
	//GET + POST �Ѵ� ����
	//@RequestMapping("/member/login") // http://localhost:8080/member/login 
	
	//����� �Է°� ��������
	// 1) HTTPServletRequest ����ϱ�
	// 2) ������� :1) �������� �� �±� name�� ��ġ 2) �� �±� name �� ��ġ���� ���� �� @RequestParm ���
	// 3) ���ε� ��ü ���
	
	
	
	
	
	
//	@RequestMapping(value="/login", method = RequestMethod.GET)
	@GetMapping("/login")
	public void LoginGet(HttpServletRequest req) {
		log.info("login..."); 
		log.info("method "+req.getMethod());
	//	return "/member/login"; // //������ �ִٸ� / WEB-INF/views/login.jsp
	}
	
	//@RequestMapping(value="/login", method = RequestMethod.POST)
//	@PostMapping("/login")
//	public void LoginPost(HttpServletRequest req) {
//		log.info("login..."); // //������ �ִٸ� / WEB-INF/views/login.jsp
//		log.info("method "+req.getMethod());
//	    // ����� �Է°� id, password
//		System.out.println("id "+req.getParameter("id"));
//		System.out.println("password "+req.getParameter("password"));
//	}
	
	@PostMapping("/login")
	public void loginPost(@RequestParam("userid") String id, String password) {
		log.info("login post..."); 
	    // ����� �Է°� id, password
		System.out.println("id "+id);
		System.out.println("password "+password);
	}
	
	//@RequestMapping("/member/register") // http://localhost:8080/member/register
	@GetMapping("/register")
	public void registerGet() {
		log.info("register..."); 
	//	return "/member/register"; // //������ �ִٸ� / WEB-INF/views/register.jsp
	}

}
