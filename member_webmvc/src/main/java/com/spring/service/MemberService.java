package com.spring.service;

import com.spring.domain.AuthDTO;
import com.spring.domain.LoginDTO;
import com.spring.domain.MemberDTO;

public interface MemberService {
	//�α���
	public AuthDTO login(LoginDTO loginDTO);
	//ȸ������
	public boolean register(MemberDTO memberDTO);
}