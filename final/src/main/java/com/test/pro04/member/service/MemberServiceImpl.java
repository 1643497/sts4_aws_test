package com.test.pro04.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.pro04.member.dao.MemberDAO;
import com.test.pro04.member.dto.MemberDTO;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService{
@Autowired
MemberDAO dao;
@Autowired
private PasswordEncoder passwordEncoder;
	@Override
	public List<MemberDTO> memberList() {
		// TODO Auto-generated method stub
		return dao.memberList();
	}
	@Override
	public void joinMember(MemberDTO dto) {
		String encodedPwd = passwordEncoder.encode(dto.getPwd());
		dto.setPwd(encodedPwd);
		dao.joinMember(dto);
	}
	@Override
	public MemberDTO selectById(String id) {
		// TODO Auto-generated method stub
		return dao.selectById(id);
	}
	@Override
	public void updateMember(MemberDTO dto) {
		dao.updateMember(dto);
		
	}
	@Override
	public void deleteMember(String id) {
		dao.deleteMember(id); 
		
	}
	@Override
	public boolean login(String id, String pwd) {
		
		return dao.login(id,pwd);
		
	}

}
