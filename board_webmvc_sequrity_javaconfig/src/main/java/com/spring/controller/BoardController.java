package com.spring.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.AttachFileDTO;
import com.spring.domain.BoardDTO;
import com.spring.domain.Criteria;
import com.spring.domain.PageDTO;
import com.spring.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	
	// ��ü ����Ʈ �����ֱ� ��Ʈ�ѷ� �ۼ� : list.jsp �����ֱ�
	
	@GetMapping("/list")
	public void listGet(Model model,@ModelAttribute("cri") Criteria cri) {
		log.info("��ü ����Ʈ ��û ");
		log.info("type "+ Arrays.toString(cri.getTypeArr()));
		
		//����� ��û�� ��ȣ�� �´� �Խù� ��� ��û
		List<BoardDTO> list  = service.getList(cri);
		//��ü �Խù� ����
		int total = service.getTotalCnt(cri);
		
		model.addAttribute("list", list);	
		model.addAttribute("pageDTO", new PageDTO(cri, total));
	}
	
	// register.jsp �����ֱ�
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/register")
	public void registerGet() {
		log.info("�۾��� �� ��û");
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/register")
	public String registerPost(BoardDTO dto,RedirectAttributes rttr,Criteria cri) {
		log.info("�۾��� ��� ��û "+dto);
		
		//÷������ Ȯ��
//		if(dto.getAttachList() != null) {
//			dto.getAttachList().forEach(attach -> log.info(attach.toString()));
//		}		
		
		if(service.insert(dto)) {
			
			log.info("�� ��ȣ : "+dto.getBno());
			
			rttr.addFlashAttribute("result", dto.getBno());
			//������ ������ ���� �ּ��ٿ� ���� ������
			rttr.addAttribute("page", cri.getPage());
			rttr.addAttribute("amount", cri.getAmount());
			return "redirect:/board/list";
		}
		return "/board/register"; 
	}
	
	//   http://localhost:8080/board/read?page=5&amount=10&bno=1
	//   http://localhost:8080/board/modify?page=5&amount=10&bno=1
	@GetMapping({"/read","/modify"})
	public void readGet(int bno,Model model,@ModelAttribute("cri") Criteria cri){
		log.info("���� ��ȸ "+bno);
		
		//bno �� �´� ���� ��������
		BoardDTO dto = service.getRow(bno);
		model.addAttribute("dto", dto);		
	}
	
	@PostMapping("/modify")
	@PreAuthorize("principal.username == #dto.writer ") //�α��� ����� == �ۼ���
	public String modifyPost(BoardDTO dto,RedirectAttributes rttr,Criteria cri) {
		log.info("���� ���� "+cri);
		//���� �� ����Ʈ
		service.update(dto);
		
		rttr.addFlashAttribute("result", "������ �Ϸ�Ǿ����ϴ�.");
		
		//������ ������ ���� �ּ��ٿ� ���� ������
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("amount", cri.getAmount());
		//�˻� ���� �ּ��ٿ� ������
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/list";		
	}
	
	// /board/remove?bno=21
	
	@GetMapping("/remove")
	@PreAuthorize("principal.username == #writer ")//�α��� ����� == �ۼ���
	public String removeGet(int bno, String writer, RedirectAttributes rttr,Criteria cri) {
		
		//�������� ÷�� ���� ����
		List<AttachFileDTO> attachList = service.getAttachList(bno);
		deleteAttachFiles(attachList);
		
		//���� �� ����Ʈ
		service.delete(bno);
		
		rttr.addFlashAttribute("result", "������ �Ϸ�Ǿ����ϴ�.");
		
		//������ ������ ���� �ּ��ٿ� ���� ������
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("amount", cri.getAmount());
		//�˻� ���� �ּ��ٿ� ������
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";		
	}
	
	// ÷������ ��������(/getAttachList) + GET + bno
	
	@GetMapping("/getAttachList")
	public ResponseEntity<List<AttachFileDTO>> attachList(int bno){
		List<AttachFileDTO> attachList = service.getAttachList(bno);
		
		return attachList!= null? new ResponseEntity<List<AttachFileDTO>>(attachList,HttpStatus.OK):
			new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	private void deleteAttachFiles(List<AttachFileDTO> attachList) {
		log.info("÷�� ���� �������� ����");
		
		if(attachList == null || attachList.size() <= 0) return;
		
		for(AttachFileDTO dto : attachList) {
			// ���� ��� 
			Path path = Paths.get("c:\\upload\\"+dto.getUploadPath()+"\\"+dto.getUuid()+"_"+dto.getFileName());
			
			try {
				Files.deleteIfExists(path);
				
				// �̹��� ������ ��� ����� ����
				if(Files.probeContentType(path).startsWith("image")) {
					Path thumb = Paths.get("c:\\upload\\"+dto.getUploadPath()+"\\s_"+dto.getUuid()+"_"+dto.getFileName());
					Files.deleteIfExists(thumb);
				}
				
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
	}
	
}






