package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.BookDTO;
import com.spring.domain.SearchDTO;
import com.spring.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/book")
@Slf4j
public class BookController {
	
	@Autowired
	private BookService service;	
	
	
	//http://localhost:8080/book/insert + GET
	//  /product/insert.jsp �����ֱ�
//	@GetMapping("/insert")
//	public String insertForm() {
//		log.info("���� ���� �߰� �� ��û");		
//		return "/product/insert";
//	}
//	
//	// http://localhost:8080/book/insert + POST 	
//	@PostMapping("/insert")
//	public String insertPost(BookDTO dto) {
//		log.info("���� ���� �߰�");
//		
//		//�Է°� �������� - HttpServletRequest, ����, ~~DTO
//		//���� �۾�
//		try {
//			boolean insertFlag = service.insert(dto);
//			if(insertFlag) {
//				//�Է� ���� �� ��� �����ֱ�
//				return "redirect:/book/list";
//			}else {
//				//�Է� ���� �� insert.jsp �����ֱ�
//				return "/product/insert";
//			}			
//		} catch (Exception e) {
//			System.out.println("����");
//			return "/product/insert";
//		}		
//	}
//	
//	
//	// http://localhost:8080/book/list + GET => list.jsp �����ֱ�
//	@GetMapping("/list")
//	public String listForm(Model model) {
//		log.info("���� ���� ��� ��û");
//		
//		//����ڰ� �ۼ��� �� ��������
//		//service �۾�
//		List<BookDTO> list = service.getList();		
//		//list �� ��� ������ list.jsp�� �����ְ� �ʹٸ�?
//		model.addAttribute("list", list); //== request.setAttribute()		
//		return "/product/list";
//	}
//	
//	// http://localhost:8080/book/read?code=1001 + GET
//	@GetMapping("/read")
//	public String readGet(int code, Model model) {
//		log.info("read ��û "+code);
//		
//		BookDTO dto = service.get(code);
//		model.addAttribute("dto", dto);
//		
//		return "/product/read";
//	}
	
	
	//http://localhost:8080/book/insert + GET
	//  /book/insert.jsp �����ֱ�
	
	@GetMapping("/insert")
	public void insertForm() {
		log.info("���� ���� �߰� �� ��û");			
	}
	
	// http://localhost:8080/book/insert + POST 	
	@PostMapping("/insert")
	public String insertPost(BookDTO dto) {
		log.info("���� ���� �߰�");
		
		//�Է°� �������� - HttpServletRequest, ����, ~~DTO
		//���� �۾�
		try {
			boolean insertFlag = service.insert(dto);
			if(insertFlag) {
				//�Է� ���� �� ��� �����ֱ�
				return "redirect:/book/list";
			}else {
				//�Է� ���� �� insert.jsp �����ֱ�
				return "/book/insert";
			}			
		} catch (Exception e) {
			System.out.println("����");
			return "/book/insert";
		}		
	}
	
	
	// http://localhost:8080/book/list + GET => book/list.jsp �����ֱ�
	@GetMapping("/list")
	public void listForm(Model model) {
		log.info("���� ���� ��� ��û");
		
		//����ڰ� �ۼ��� �� ��������
		//service �۾�
		List<BookDTO> list = service.getList();		
		//list �� ��� ������ list.jsp�� �����ְ� �ʹٸ�?
		model.addAttribute("list", list); //== request.setAttribute()		
	}
	
	// http://localhost:8080/book/read?code=1001 + GET => book/read.jsp
	//  http://localhost:8080/book/modtfy?code=1006 => book/modify.jsp
	@GetMapping({"/read","/modify"})
	public void readGet(int code, Model model) {
		log.info("read ��û "+code);
		
		BookDTO dto = service.get(code);
		model.addAttribute("dto", dto);	
		//View Resolver ���� http://localhost:8080/book/read = > WEB-INF/views/book/read.jsp
		//                                 http://localhost:8080/book/modify = > WEB-INF/views/book/modify.jsp
	}
	
//	@GetMapping("/modify")
//	public void modifyGet(int code, Model model) {
//		log.info("read ��û "+code);
//		
//		BookDTO dto = service.get(code);
//		model.addAttribute("dto", dto);	
//	}
	
	//   http://localhost:8080/book/modify + POST
	@PostMapping("/modify")
	public String updatePost(BookDTO dto, RedirectAttributes rttr) {
		log.info("���� ���� ���� "+dto);
		
		service.update(dto);
		
		rttr.addAttribute("code", dto.getCode());
		
		//              /book/read�� �������ֱ�
		return "redirect: /book/read";
	}
	@GetMapping("/remove")
	public String removeGet(int code) {
		log.info("���� ���� ���� "+code);
		
		//���� �� ��� �����ֱ�
		service.delete(code);
		return "redirect: /book/list";		
	}
	// ����� �Է� �� �������� => ���� ���
//	@GetMapping("/search")
//	public String searchGet(String criteria, String keyword,Model model) {
//		log.info("���� ���� �˻� "+criteria+", "+keyword);
//		
//		List<BookDTO> list = service.getSearchList(criteria, keyword);
//		model.addAttribute("list", list);
//		
//		return "/book/list";
//	}
	
	@GetMapping("/search")
	public String searchGet(SearchDTO search,Model model) {
		log.info("���� ���� �˻� "+search);
		
		List<BookDTO> list = service.getSearchList(search.getCriteria(), search.getKeyword());
		model.addAttribute("list", list);
		model.addAttribute("searchDTO", search);
		
		return "/book/list";
	}
	
}
