package com.spring.dependency;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TVMain {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		// TV tv =  (TV) ctx.getBean("samsungTV");   @Componet �� ������ id�� Ŭ������ ���(���ڸ��� �ҹ���)
		TV tv =  (TV) ctx.getBean("tv");  //@Componet(tv)
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
	}

}
