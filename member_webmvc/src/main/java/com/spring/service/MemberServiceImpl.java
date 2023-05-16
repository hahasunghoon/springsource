package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.domain.AuthDTO;
import com.spring.domain.LoginDTO;
import com.spring.domain.MemberDTO;
import com.spring.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder bPasswordEncoder;

	@Override
	public AuthDTO login(LoginDTO loginDTO) {	
		// 1234 ==> bPasswordEncoder.encode(1234) ==> ��ȣȭ
		// matches(��ȣȭ�ϱ� �� �ڵ�, ��ȣȭ�� �ڵ�)
		// matches(1234,db��ȣȭ�� ����)
		
		// ��й�ȣ�� ��ġ�ϴ°�?
		// db���� ��ȣȭ�� ��� ��������
		String encodePass = mapper.getPass(loginDTO.getUserid());
		
		if(bPasswordEncoder.matches(loginDTO.getPassword(), encodePass)) {
			return mapper.login(loginDTO.getUserid());			
		}else {
			return null;
		}		
	}
	
	@Override
	public boolean register(MemberDTO memberDTO) {
		// ��й�ȣ ��ȣȭ : encode(��ȣȭ�� ���� �ڵ�)
		//                   matches(��ȣȭ�ϱ� ��, ��ȣȭ �ڵ�)
		//                   1234, ��ȣȭ�� �ڵ�
		memberDTO.setPassword(bPasswordEncoder.encode(memberDTO.getPassword()));
		
		return mapper.insert(memberDTO)==1?true:false;
	}

}
