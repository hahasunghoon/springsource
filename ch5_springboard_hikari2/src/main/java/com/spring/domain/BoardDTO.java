package com.spring.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter @ToString
public class BoardDTO {
	    private  int bno;
		private String title;
		private String content;
		private String writer;
		private Date regdate;
		private Date updatedate;
		


}
