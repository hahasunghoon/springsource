package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.domain.Criteria;
import com.spring.domain.ReplyDTO;
import com.spring.service.ReplyService;

import lombok.extern.slf4j.Slf4j;

// http://localhost:8080/replies/

@Slf4j
@RestController
@RequestMapping("/replies")
public class ReplyController {
	
	@Autowired
	private ReplyService reService;

	
	// http://localhost:8080/replies/1 + GET : 1¹ø ´ñ±Û µ¥ÀÌÅÍ °¡Á®¿À±â
	@GetMapping(value="/{rno}")
	public ResponseEntity<ReplyDTO> get(@PathVariable("rno") int rno){
		log.info("´ñ±Û Á¶È¸ "+rno);
		
		return new ResponseEntity<ReplyDTO>(reService.read(rno), HttpStatus.OK);
	}
	
	
	// http://localhost:8080/replies/new + POST + ÀÔ·Âµ¥ÀÌÅÍ(json)
	@PostMapping("/new")
	public ResponseEntity<String> create(@RequestBody ReplyDTO dto){
		log.info("´ñ±Û »ðÀÔ "+dto);
		
		return reService.insert(dto)?
				new ResponseEntity<String>("success", HttpStatus.OK):
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	// http://localhost:8080/replies/pages/bno/ + GET
	
	@GetMapping("/pages/{bno}/{page}")
	public ResponseEntity<List<ReplyDTO>> select(@PathVariable("bno") int bno,@PathVariable("page")int page){
		log.info("´ñ±Û Á¶È¸ "+bno);
		
		Criteria cri = new Criteria(page, 10);
		
		return new ResponseEntity<List<ReplyDTO>>(reService.getList(cri,bno), HttpStatus.OK);
	}
	
	// http://localhost:8080/replies/rno + PUT + ¼öÁ¤µ¥ÀÌÅÍ(json)
	
	@PutMapping("/{rno}")
	public ResponseEntity<String> modify(@RequestBody ReplyDTO dto){
		log.info("´ñ±Û ¼öÁ¤ "+dto);
		
		return reService.update(dto)?
				new ResponseEntity<String>("success", HttpStatus.OK):
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		
	}
	
	// http://localhost:8080/replies/rno + DELETE : ´ñ±Û »èÁ¦
	@DeleteMapping("/{rno}")
	public ResponseEntity<String> remove(@PathVariable("rno") int rno){
		log.info("´ñ±Û »èÁ¦ "+rno);
		
		return reService.delete(rno)?
				new ResponseEntity<String>("success", HttpStatus.OK):
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	
		
	}
}
