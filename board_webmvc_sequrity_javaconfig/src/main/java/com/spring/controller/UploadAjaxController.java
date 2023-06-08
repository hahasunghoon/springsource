package com.spring.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		log.info("upload ��û ");
		
		List<AttachFileDTO> fileList = new ArrayList<AttachFileDTO>();
		
		String uploadPath = "e:\\upload";
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
		
		File file = new File("e:\\upload\\"+fileName);
		
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
	
	// HttpServletRequest ��ü : Ŭ���̾�Ʈ ������ ������ �� ����
	@GetMapping(value="/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadFile(String fileName,@RequestHeader("User-Agent") String userAgent){
		log.info("���� �ٿ�ε� ��û "+fileName);
		
		// c:\\upload\\2023\05\30\4be2993f-a046-42ed-bc9c-aa4cd02c048e_cat1.jpg
		Resource resource = new FileSystemResource("e:\\upload\\"+fileName);
		
		// uuid�� ������ ���ϸ� 
		String oriFileName = resource.getFilename();
		// uuid �� ������ ���ϸ�
		String splitUuid = oriFileName.substring(oriFileName.indexOf("_")+1);
		
		if(!resource.exists()) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		
		HttpHeaders headers = new HttpHeaders();
		
		String downloadName = null;
		try {
				//ms �迭 : Trident (IE 11)
				if(userAgent.contains("Trident") || userAgent.contains("Edge")) {
					// uuid �� ������ ���ϸ�
					// downloadName = URLEncoder.encode(resource.getFilename(), "utf-8").replaceAll("\\+", " ");				
					
					downloadName = URLEncoder.encode(splitUuid, "utf-8").replaceAll("\\+", " ");
				}else {
					// downloadName = new String(resource.getFilename().getBytes("utf-8"), "ISO-8859-1");
					
					downloadName = new String(splitUuid.getBytes("utf-8"), "ISO-8859-1");
				}
				
				//������ ����� ���̱�
				headers.add("Content-Disposition", "attachment;filename="+downloadName);
		} catch (UnsupportedEncodingException e) {				
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName, String type){
		log.info("���� ���� ��û "+fileName+", type "+type);
		// Ư������ ���ڵ��� �Ͼ ==> ��ο� �ִ� \ �� %5C �� ���ڵ� ��
		// 2023%5C05%5C30%5Cdd654659-e814-478e-b103-c3812efb375d_test.txt
		// 2023%5C05%5C30%5Cs_a7627020-448f-4143-a986-ecfcd23d95b1_cat1.jpg, type image
		
		try {
			File file = new File("e:\\upload\\", URLDecoder.decode(fileName, "utf-8"));
			
//			�̹��� : ����, ����� �̹��� ����
//			txt : ���� ����
			
			file.delete();  // txt ����, ����� ����
			
			//���� �̹��� ����
			if(type.equals("image")) {
				// ������ �ۼ��� file ��ü���� fileName ���� �� s_�� ������ ������ �̸��� ���� ��ü�� ����
				String largeName = file.getAbsolutePath().replace("s_", "");
				file = new File(largeName);
				// delete �۾�
				file.delete();
			}			
		} catch (UnsupportedEncodingException e) {	
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
		return new ResponseEntity<String>("success",HttpStatus.OK);
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