package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller //@Component 자식
@RequestMapping("/sample")
public class BasicController { //option ,   BasicController는 http://localhost:8080/sample/** 요청에 응답하는 컨트롤러
	//컨트롤러 안에 일반 메소드 작성 가능
	// 일반 메소드는 @RequestMapping 어노테이션을 붙이지 않으면 됌
	//@RequestMapping("/basic")
	@GetMapping("/basic")
	public void basic() {
		log.info("basic.....요청");
		
		// 컨트롤러 실행후(없을 수도있음) 실행결과에 맞춰 ViewResolver 가 실행됨
		// 리턴이 있다면  WEB_INF/views/리턴값.jsp
        // 리턴이 없다면 주소줄 문자열       /WEB-INF/views/sample/basic.jsp 으로 만들어서 결과 리턴
		// [WEB_INF/views/sample/basic.jsp]
	}

}
