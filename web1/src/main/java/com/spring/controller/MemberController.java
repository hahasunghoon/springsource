package com.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.domain.LoginDTO;
import com.spring.domain.RegisterDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller //http://localhost:8080 요청 응답 컨트롤러
@RequestMapping("/member")
public class MemberController {
	
	//GET + POST 둘다 응답
	//@RequestMapping("/member/login") // http://localhost:8080/member/login 
	
	//사용자 입력값 가져오기
	// 1) HTTPServletRequest 사용하기
	// 2) 변수사용 :1) 변수명은 폼 태그 name과 일치 2) 폼 태그 name 과 일치하지 않을 떄 @RequestParm 사용
	// 3) 바인딩 객체 사용
	

//	@RequestMapping(value="/login", method = RequestMethod.GET)
	@GetMapping("/login")
	public void LoginGet(HttpServletRequest req) {
		log.info("login..."); 
		log.info("method "+req.getMethod());
	//	return "/member/login"; // //리턴이 있다면 / WEB-INF/views/login.jsp
	}
	
	//@RequestMapping(value="/login", method = RequestMethod.POST)
//	@PostMapping("/login")
//	public void LoginPost(HttpServletRequest req) {
//		log.info("login..."); // //리턴이 있다면 / WEB-INF/views/login.jsp
//		log.info("method "+req.getMethod());
//	    // 사용자 입력값 id, password
//		System.out.println("id "+req.getParameter("id"));
//		System.out.println("password "+req.getParameter("password"));
//	}
	
//	@PostMapping("/login")
//	public void loginPost(@RequestParam("userid") String id, String password) {
//		log.info("login post..."); 
//	    // 사용자 입력값 id, password
//		System.out.println("id "+id);
//		System.out.println("password "+password);
//	}
	
	@PostMapping("/login")
	public String loginPost(LoginDTO dto) {
		log.info("login post..."); 
	    // 사용자 입력값 id, password
		System.out.println("id "+dto.getId());
		System.out.println("password "+dto.getPassword());
		
		//main.jsp로 보여주기
		return "/member/main";
	}
	
	//@RequestMapping("/member/register") // http://localhost:8080/member/register
	@GetMapping("/register")
	public void registerGet() {
		log.info("register..."); 
	//	return "/member/register"; // //리턴이 있다면 / WEB-INF/views/register.jsp
	}

	 // /member/register + POST처리
	// DTO작성
	//사용자 입력값
	// login.jsp 보여주기
	@PostMapping("/register")
	public String resisterPost(RegisterDTO dto) {
		log.info("회원가입 요청");
		log.info(dto.toString());
		
		//redirect : 붙게되면 DispatcherServlet 이 동작  =>다시한번 경로이동
		// ==response.sendRedirect()
		//http://localhost:8080/member/login + get요청
		return "redirect:/member/login";
	}
}
