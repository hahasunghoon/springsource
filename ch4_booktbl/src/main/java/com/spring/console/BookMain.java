package com.spring.console;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.spring.domain.BookDTO;
import com.spring.persistence.BookDAO;
import com.spring.service.BookService;
import com.spring.service.BookServiceImpl;

public class BookMain {
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
//		
//		//Service ȣ��
		BookService service = (BookService) ctx.getBean("bookService");
		
		//���� �߰�
//		BookDTO insertDto = new BookDTO(1001, "�̰��� �ڹٴ�", "�ſ��", 25000, "null");
//		if(service.insertBook(insertDto)) {
//			System.out.println("�Է¼���");
//		}	
		
		
		//���� ����
		BookDTO updateDto = new BookDTO();
		updateDto.setCode(1001);
		updateDto.setPrice(26000);
		if(service.updateBook(updateDto)) {
			System.out.println("��������");
		}
		
		//Ư�� ���� ��ȸ
		BookDTO row = service.getBook(1001);
		System.out.println(row);
		
		//���� ����
		if(service.deleteBook(1008)) {
			System.out.println("��������");
		}
		
		//��ü ���� ��� ��������
		// BookService service = new BookServiceImpl(new BookDAO());
		List<BookDTO> list  = service.getBookList();
		
		for (BookDTO bookDTO : list) {
			System.out.println(bookDTO);
		}
	}
}

	
	
