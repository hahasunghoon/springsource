package com.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.domain.SampleDTO;

@RestController
public class RestControllerTest {
	
	// �Ϲ� ��Ʈ�ѷ����� return ���� view �̸�
	// Rest ��Ʈ�ѷ����� return ���� ������
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello World";
	}
	
	// produces : ���� ���� json ���·� �����ž�
	// �ڹ� ��ü ==> json ���·� �������ִ� ���̺귯���� �ʿ���
	@GetMapping(value="/send",produces = MediaType.APPLICATION_JSON_VALUE)
	public SampleDTO sendDto() {
		return new SampleDTO("1234", "ȫ", "�浿");
	}
	
	
	@GetMapping(value="/sends",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SampleDTO> sendList() {
		
		List<SampleDTO> list = new ArrayList<SampleDTO>();
		list.add(new SampleDTO("1234", "ȫ", "�浿"));
		list.add(new SampleDTO("1235", "��", "�浿"));
		list.add(new SampleDTO("1236", "��", "�浿"));
		list.add(new SampleDTO("1237", "��", "�浿"));
		
		return list;
	}
	
	//  ResponseEntity : �����ڵ�(200,400,500)�� ������ ���� ����
	@GetMapping(value="/check",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SampleDTO> check(double height, double weight) {
		
		SampleDTO dto = new SampleDTO("1234", ""+height, ""+weight);
		
		ResponseEntity<SampleDTO> entity = null;
		
		if(height < 160) {
			entity = new ResponseEntity<SampleDTO>(dto, HttpStatus.BAD_REQUEST);
		}else {
			entity = new ResponseEntity<SampleDTO>(dto, HttpStatus.OK);
		}	
		
		return entity;
	}
	
	//��ο� �ִ� Ư�� ���� ����ϰ� �ʹٸ�?
	// http://localhost:8080/product/bags/1001 : bags(ī�װ�����), 1001(��ǰ ���̵�)
	// http://localhost:8080/product/shirt/1003 : bags(ī�װ�����), 1001(��ǰ ���̵�)
	
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat,@PathVariable("pid") String pid) {
		return new String[] {
				"category : "+cat,
				"productId : "+pid
		};
	}
}