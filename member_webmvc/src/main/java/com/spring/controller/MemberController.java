package com.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.AuthDTO;
import com.spring.domain.LoginDTO;
import com.spring.domain.MemberDTO;
import com.spring.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	
	//login.jsp �����ִ� ��Ʈ�ѷ� �ۼ�
	@GetMapping("/login")
	public void loginGet() {
		log.info("�α��� �� ��û");
	}
	
	@PostMapping("/login")
	public String loginPost(LoginDTO loginDTO,HttpSession session) {
		log.info("�α��� ��û "+loginDTO);
		
		AuthDTO authDTO = service.login(loginDTO);
		
		if(authDTO != null) {
			//session �α��� ���� ���
			session.setAttribute("authDTO", authDTO);
			//index �̵�
			return "redirect:/";			
		}else {
			return "redirect:/member/login";
		}
		
	}
	
	
	@GetMapping("/logout")
	public String logoutGet(HttpSession session) {
		log.info("�α׾ƿ� ��û");
		
		session.removeAttribute("authDTO");
		
		//session ���� index �̵�
		return "redirect:/";
	}
	
	@GetMapping("/step1")
	public void stepGet() {
		// member/step1.jsp �����ֱ�
		log.info("��� ������ �����ֱ�");		
	}
	
	@PostMapping("/step1")
	public String stepPost(boolean agree, RedirectAttributes rttr){
		log.info("������� "+agree);
		// ������� ����
		
		// true �ΰ�� register.jsp �����ֱ�
		if(agree) return "/member/register";
		// false �� ��� step1.jsp �����ֱ�
		else {
			rttr.addFlashAttribute("check", false);
			return "redirect:/member/step1";
			//return "/member/step1"; // ==> View Resolver
		}		
	}
	
	@PostMapping("/register")
	public String registerPost(MemberDTO dto) {
		log.info("ȸ������ ��û "+ dto);
		
		//ȸ������ ���� �� �α��� ������ �̵�
		if(service.register(dto)) {
			return "redirect:/member/login";
		}else {
			return "/member/register";
		}
	}
}
