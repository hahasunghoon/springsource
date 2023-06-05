package com.spring.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) //�׽�Ʈ�ڵ尡 �����������ӿ�ũ �ȿ��� ����
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	                   "file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class MemberAuthTest {

	@Autowired
	private DataSource ds;
	
	@Test //�׽�Ʈ �޼ҵ����� �˷���(�Ķ���� ����, ����Ÿ�� ����)
	public void test() {
		String sql ="insert into spring_member_auth(userid,auth) values(?,?)";
		
		for (int i = 0; i < 50; i++) {
			
			try (Connection con = ds.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)){
					
				// 50�� ȸ�� => �Ϲ� ȸ�� 20��, �Ŵ���20��, ������ 10��
				
				if(i < 20) {
					pstmt.setString(1, "user"+i);
					pstmt.setString(3, "ROLE_MEMBER");
				}else if(i < 40) {
					pstmt.setString(1, "manager"+i);
					pstmt.setString(3, "ROLE_MANAGER");
				}else {
					pstmt.setString(1, "admin"+i);
					pstmt.setString(3, "ROLE_ADMIN");
				}
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}
}