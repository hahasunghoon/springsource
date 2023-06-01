package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.domain.AddDTO;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class CalcController {
	
	@GetMapping("/add")
	public void addForm() {
		log.info("add 요청");
	}
	
	    //사용자 입력값 가져오기
		// 1) HTTPServletRequest 사용하기
		// 2) 변수사용 :1) 변수명은 폼 태그 name과 일치 2) 폼 태그 name 과 일치하지 않을 떄 @RequestParm 사용
		// 3) 바인딩 객체 사용
	
//	@PostMapping("/add")
//	public void addFost(int num1, int num2) {
//		log.info("add post 요청");
//		log.info("num1 "+num1);
//		log.info("num2 "+num2);
//		log.info("result " +num1+num2);
//	}
	
//	@PostMapping("/add")
//	public void addFost(AddDTO dto) {
//		log.info("add post 요청");
//		log.info("num1 "+dto.getNum1());
//		log.info("num2 "+dto.getNum2());
//		log.info("result " +(dto.getNum1()+dto.getNum2()));
//	}
	
	@PostMapping("/add")
	public String addFost(@ModelAttribute("add") AddDTO dto,@ModelAttribute("page") String page,Model model) {
		log.info("add post 요청");
		log.info("num1 "+dto.getNum1());
		log.info("num2 "+dto.getNum2());
		log.info("page "+page);
		int result = dto.getNum1()+dto.getNum2();
		log.info("result " +result);  
          //==> result값을 result.jsp에서 사용하고 싶다면?
		  //==> ★★Model 객체 사용 ==request.setAttribut() 랑 같다
		
	    model.addAttribute("result", result);
	   // model.addAttribute("page", page);
		
		return "result"; // /WEB-INF/views/result.jsp
	}
}
