package com.test.pro04.member.service;

import java.util.List;

import com.test.pro04.member.dto.MemberDTO;

public interface MemberService {

	List<MemberDTO> memberList();

	void joinMember(MemberDTO dto);

	MemberDTO selectById(String id);

	void updateMember(MemberDTO dto);

	void deleteMember(String id);

	boolean login(String id, String pwd);

}
