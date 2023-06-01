package com.spring.schedule;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spring.domain.AttachFileDTO;
import com.spring.mapper.AttachMapper;

@Component
public class TaskTest {
	
	@Autowired
	private AttachMapper mapper;
	
	// second
	// minute
	// hour
	// day of month
	// month
	// day of week	
	@Scheduled(cron="0 0 2 * * *")
	public void schedulerTest() {
		
		//db���� ���� ��¥�� ���� ��� �������� 
		List<AttachFileDTO> oldList = mapper.oldFiles();
		
		//oldList ==> ��η� �����ؾ� ��
		//�̹��� �����̶�� ���ϸ�� + ����� ��� �߰�		
		List<Path> pathList = new ArrayList<Path>();
		
		for (AttachFileDTO dto : oldList) {
			Path path = Paths.get("c:\\upload\\"+dto.getUploadPath()+"\\"+dto.getUuid()+"_"+dto.getFileName());
			pathList.add(path);
			
			if(dto.isFileType()) {
				Path thumb = Paths.get("c:\\upload\\"+dto.getUploadPath()+"\\s_"+dto.getUuid()+"_"+dto.getFileName());
				pathList.add(thumb);
			}
		}
		System.out.println(pathList);
		
		//������¥ ���ؼ� ������ ������ �� ������ �ִ� ���� ��� ��������
		String yesterday = getFolderYesterDay();
		
		File targetDir = Paths.get("c:\\upload",yesterday).toFile();
		System.out.println("targetDir "+targetDir);
		
		//���� ��ġ���� ���� ���� ����
		File[] removeFiles = targetDir.listFiles(f -> pathList.contains(f.toPath())==false);
		
		for(File remove:removeFiles) {
			remove.delete();
		}
		
	}
	
	private String getFolderYesterDay() {
		
		//������¥ ����
		LocalDate yesterday = LocalDate.now().minusDays(1);
		
		String str = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		// 2023-05-31 => 2023/05/31
		return str.replace("-", File.separator); //�ü���� �´� ���� ������(/, \) �� ����
	}
	
	
	
	
	
	
//	@Scheduled(fixedDelay = 10000) 
//	public void schedulerTest2() {
//		System.out.println("10�ʸ��� �����층....");
//	}
}


