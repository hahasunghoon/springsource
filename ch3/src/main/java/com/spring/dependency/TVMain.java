package com.spring.dependency;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TVMain {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		// TV tv =  (TV) ctx.getBean("samsungTV");   @Componet 로 생성시 id는 클래스명 사용(앞자리는 소문자)
		TV tv =  (TV) ctx.getBean("tv");  //@Componet 
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
	}

}
