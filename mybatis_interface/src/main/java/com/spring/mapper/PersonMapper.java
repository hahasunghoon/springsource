package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import com.spring.domain.PersonDTO;

public interface PersonMapper {

// interface + ������̼�
//	@Insert("insert into person(id,name) values(#{id},#{name})")
//	public int insert(PersonDTO insert);
	
	// interface + XML
	// �ڡڡڸ޼ҵ��� XML �� �ۼ��� id ���� ��ġ�ؾ���
	public int insertPerson(PersonDTO insert);
	public int updatePerson(PersonDTO insert);
	public int deletePerson(String id);
	public PersonDTO selectOne(String id);
	public List<PersonDTO> selectAll();
}
