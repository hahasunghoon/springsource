package com.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.BoardDTO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
	//@RequestMapping(value="/read", method =RequestMethod.GET)
	@GetMapping("/read")
	public void readGet(HttpServletRequest req) {
		log.info("read ��û");
	}
	//@RequestMapping(value="/register", method =RequestMethod.GET)
	@GetMapping("/register")
	public void registerGet(HttpServletRequest req) {
		log.info("register ��û");
	}
	
	//Model == request.setAttribur()
	//RedirctAttributes : 
	// 1. addAttribute() - ��ȸ������ �����͸� ����(��ü ���·� �� ���� �Ұ� - �ּ��ٿ� ���󰡴� ���)
	// 2. addFlashAttribute() - ��ȸ������ ������ ����(session �� ����� - ��ü ���·� ���� ����)
	
	
	// HttpSession
	
	// BoardDTO
	// read �� �̵�
//	@PostMapping("/register")
//	public String registerPost(BoardDTO dto,RedirectAttributes rttr) {
//		log.info("register ��û "+dto);
//		
//		//BoardDTO ���� ������ �Ƿ��� forward �� ����������
//		// BoardDTO ��ü�� ��� ���� read.jsp���� ����ϰ� �ʹٸ�?
////		rttr.addAttribute("name", dto.getName());
////		rttr.addAttribute("password", dto.getPassword());
////		rttr.addAttribute("title", dto.getTitle());
////		rttr.addAttribute("content", dto.getContent());
//		
//		rttr.addFlashAttribute("boardDTO", dto);
//		
//		return "redirect: /board/read";
//	}
	
	//@ModelAttribute("dto") : ��ȣ �κ��� ���� ����
	//                                           Model ��ü ��� ���
	//                                          ������ ��ü�� ���� �� �̸� ���� ����
	//  registerPost(BoardDTO dto) : jsp ���������� ���� �����ö� ${boardDTO.name}
	// registerPost(@ModelAttribute("dto") BoardDTO dto) : jsp ���������� ���� �����ö� ${dto.name}
	@PostMapping("/register")
	public void registerPost(@ModelAttribute("dto") BoardDTO dto,RedirectAttributes rttr) {
		log.info("register ��û "+dto);
		
		//BoardDTO ���� ������ �Ƿ��� forward �� ����������
		// BoardDTO ��ü�� ��� ���� read.jsp���� ����ϰ� �ʹٸ�?

	}
	
	

	//@RequestMapping(value="/modify", method =RequestMethod.GET)
	@GetMapping("/modify")
	public void modifyGet(HttpServletRequest req) {
		log.info("modify ��û");
	}
	//@RequestMapping(value="/ remove", method =RequestMethod.GET)
	@GetMapping("/remove")
	public void removeGet(HttpServletRequest req) {
		log.info(" remove��û");
	}

}