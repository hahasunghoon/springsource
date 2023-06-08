package com.spring.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
	
	// /member/login
	@GetMapping("/login")
	public void loginGet() {
		log.info("�α��� �� ��û ");
	}

	@GetMapping("/login-error")
	public String loginError(Model model) {
		
		model.addAttribute("error", "���̵� ��й�ȣ�� Ȯ���� �ּ���");
		
		return "/member/login";
	}
	
	@GetMapping("/admin")
	public void admingGet() {
		log.info("admin ��û");
	}
	
	@GetMapping("/auth")
	@ResponseBody
	public Authentication auth() {		
		return SecurityContextHolder.getContext().getAuthentication(); 
	}
}
