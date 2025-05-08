package com.test.pro04.member.dao;

import java.util.List;

import com.test.pro04.member.dto.MemberDTO;

public interface MemberDAO {

	List<MemberDTO> memberList();

	void joinMember(MemberDTO dto);

	MemberDTO selectById(String id);

	void updateMember(MemberDTO dto);

	void deleteMember(String id);

	boolean login(String id, String pwd);

}
