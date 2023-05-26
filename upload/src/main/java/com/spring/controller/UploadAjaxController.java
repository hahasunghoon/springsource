package com.spring.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import com.spring.domain.AttachFileDTO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.builders.BufferedImageBuilder;

@Controller
@Slf4j
public class UploadAjaxController {

	//uploadAjax ��Ʈ�ѷ� ����
	@GetMapping("/uploadAjax")
	public void uploadAjaxGet() {
		log.info("uploadAjax �� ��û");
	}
	
//	@PostMapping("/uploadAjax")
//	public ResponseEntity<String> uploadAjaxPost(MultipartFile uploadFile) {
//		log.info("upload ��û ");
//		
//		log.info("file name "+uploadFile.getOriginalFilename());
//		log.info("file size "+uploadFile.getSize());
//		
//		String uploadPath = "c:\\upload";
//		UUID uuid = UUID.randomUUID();
//		String fileName = uuid.toString() + "_" + uploadFile.getOriginalFilename();
//		
//		try {
//			uploadFile.transferTo(new File(uploadPath,fileName));
//		} catch (IllegalStateException e) {			
//			e.printStackTrace();
//		} catch (IOException e) {			
//			e.printStackTrace();
//		}
//		//�����ڵ� + �޼���
//		return new ResponseEntity<>(fileName, HttpStatus.OK);
//	}
	
	
//	@PostMapping("/uploadAjax")
//	public ResponseEntity<List<String>> uploadAjaxPost(MultipartFile[] uploadFile) {
//		log.info("upload ��û ");
//		
//		List<String> fileList = new ArrayList<String>();
//		
//		for (MultipartFile multipartFile : uploadFile) {
//			log.info("file name "+multipartFile.getOriginalFilename());
//			log.info("file size "+multipartFile.getSize());
//			
//			String uploadPath = "c:\\upload";
//			UUID uuid = UUID.randomUUID();
//			String fileName = uuid.toString() + "_" + multipartFile.getOriginalFilename();
//			
//			fileList.add(multipartFile.getOriginalFilename());
//			
//			try {
//				multipartFile.transferTo(new File(uploadPath,fileName));
//			} catch (IllegalStateException e) {			
//				e.printStackTrace();
//			} catch (IOException e) {			
//				e.printStackTrace();
//			}			
//		}		
//		//�����ڵ� + �޼���
//		return new ResponseEntity<>(fileList, HttpStatus.OK);
//	}
	
	
	@PostMapping("/uploadAjax")
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		log.info("upload ��û ");
		
		List<AttachFileDTO> fileList = new ArrayList<AttachFileDTO>();
		
		String uploadPath = "c:\\upload";
		//���� ���� => c:\\upload\\2023\\05\\26 
		String uploadFolderPath = getFolder();
		log.info("uploadFolderPath ",uploadFolderPath);
		
		File uploadFullPath = new File(uploadPath,uploadFolderPath);
		
		if(!uploadFullPath.exists()) {
			uploadFullPath.mkdirs();
		}	
		
		for (MultipartFile multipartFile : uploadFile) {
			log.info("file name "+multipartFile.getOriginalFilename());
			log.info("file size "+multipartFile.getSize());
			
			UUID uuid = UUID.randomUUID();
			String fileName = uuid.toString() + "_" + multipartFile.getOriginalFilename();		
			
			File saveFile = new File(uploadFullPath,fileName);	
			
			// ���� �� ���� AttachFileDTO ����
			AttachFileDTO attach = new AttachFileDTO();
			attach.setFileName(multipartFile.getOriginalFilename());
			attach.setUploadPath(uploadFolderPath); 
			attach.setUuid(uuid.toString());
			
			try {
				//���� ���� ����
				multipartFile.transferTo(saveFile);
				
				//���ε�� ���� Ÿ�� üũ
				if(checkImageType(saveFile)) {
					attach.setFileType(true);
					
					//�̹��� �����̶�� ����� �̹����� ����
					//���� �̹��� �о����				
					
					BufferedImage origin = ImageIO.read(saveFile);				
					
					//����� ���ϸ� 
					File thumbnail = new File(uploadFullPath,"s_"+fileName);	
										
					double ratio = 10; //��Һ���					
					int width = (int)(origin.getWidth() / ratio);
					int height = (int)(origin.getHeight() / ratio);
					
					Thumbnails.of(origin).size(width, height).toFile(thumbnail);
				}			
			} catch (Exception e) {			
				e.printStackTrace();
			}			
			fileList.add(attach);
		}		
		//�����ڵ� + �޼���
		return new ResponseEntity<>(fileList, HttpStatus.OK);
	}
	
	
	//���� ��û �� ���� �����ֱ�
	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(String fileName){
		log.info("���� ��û "+fileName);
		
		
		File file = new File("c:\\upload\\"+fileName);
		
		//springframework Headers
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<byte[]> result = null;
		try {
			// ������ ������ ���Ͽ� ���� Ÿ�� ����
			headers.add("content-type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file),HttpStatus.OK);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return result;
	}
	@GetMapping(value="/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadFile(String fileName,@RequestHeader("User-Agent") String userAgent){
		log.info("���� �ٿ�ε� ��û "+fileName);
		
		Resource resource = new FileSystemResource("c:\\upload\\"+fileName);
		
		if(!resource.exists()) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		
		HttpHeaders headers = new HttpHeaders();
		
		String downloadName = null;
		try {
				if(userAgent.contains("Trident") || userAgent.contains("Edge")) {
					downloadName = URLEncoder.encode(resource.getFilename(), "utf-8").replaceAll("\\+", " ");
				}else {
					downloadName = new String(resource.getFilename().getBytes("utf-8"), "ISO-8859-1");
				}
				
				headers.add("Content-Disposition", "attachment;filename="+downloadName);
		} catch (UnsupportedEncodingException e) {				
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
	}
	
	
	
	
	
	//�Ϲ� �޼ҵ�(���� Ÿ�� Ȯ��)
	private boolean checkImageType(File file) {
		String contentType;
		try {
			contentType = Files.probeContentType(file.toPath()); // image/gif, image/jpg, 
			return contentType.startsWith("image");
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return false;
	}
	//�Ϲ� �޼ҵ�(���� ����)
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date(); // ��¥ ������ ��� 
		String str = sdf.format(date); // 2023-05-26
		return str.replace("-", File.separator); // c:/1.jpg
	}
	
}