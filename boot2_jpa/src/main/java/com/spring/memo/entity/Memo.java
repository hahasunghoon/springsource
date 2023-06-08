package com.spring.memo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity // 클래스를 엔티티로 선언
@AllArgsConstructor
@NoArgsConstructor
@Getter 
@Setter 
@ToString
@Builder
@SequenceGenerator(name="men_seq_gen", sequenceName = "men_seq",allocationSize = 1)
public class Memo {
	
	//name : 임의로 이름 설정(필수), sequenceName : men_seq_nextval, allocationSize= 증가할 숫자
	//@SequenceGenerator(name="men_seq_gen", sequenceName = "men_seq",allocationSize = 1)
	
	//@GeneratedValue : 1)Auto : JPA 구현체가 자동으로 구현, 2)IDENTITY : 기본키 생성을 데이터베이스에 위임
    //                  3)SEQUENCE : SequenceGenerator 등록 후 사용
	//                  4)TABLE : 키 생성용 테이블 사용 @TableGenerator 사용
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "men_seq_gen")
	@Id //pk 생성
	private Long mno;
	
	@Column(length = 200, nullable = false)
	private String memoText;

}
