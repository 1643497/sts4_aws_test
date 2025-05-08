package com.test.pro04.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.pro04.member.dto.MemberDTO;
import com.test.pro04.member.service.MemberService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("member")
public class MemberControllerImpl implements MemberController{
@Autowired
MemberService service;
	@Override
	@GetMapping("memberList")
	public String memberList(Model model,HttpSession session) {
		List<MemberDTO> memberList = service.memberList();
		String userId = (String)session.getAttribute("userId");
		model.addAttribute("userId",userId);
		model.addAttribute("memberList",memberList);
		return "/member/memberList";
	}
	@Override
	@GetMapping("joinMember")
	public String joinMember() {
		
		
		return "/member/joinMember";
	}
	
	@Override
	@PostMapping("joinMember")
	public String joinMember(MemberDTO dto,Model model) {
		service.joinMember(dto);
		model.addAttribute("message","회원가입 완료");
		model.addAttribute("redirectUrl","/member/memberList");
		return "/common/alert";
	}

	@Override
	@GetMapping("detailMember")
	public String detailMember(String id,Model model) {
		MemberDTO member = service.selectById(id);
		model.addAttribute("member",member);
		return "/member/detailMember";
	}
	@Override
	@PostMapping("detailMember")
	public String updateMember(MemberDTO dto, Model model) {
		service.updateMember(dto);
		model.addAttribute("message","수정이 완료되었습니다");
		model.addAttribute("redirectUrl","/member/memberList");
		return "/common/alert";
	}
	@Override
	@PostMapping("deleteMember")
	public String deleteMember(String id, Model model) {
		service.deleteMember(id);
		model.addAttribute("message","삭제가 완료되었습니다");
		model.addAttribute("redirectUrl","/member/memberList");
		return "/common/alert";
	}
	@Override
	@GetMapping("login")
	public String login() {
		
		return "/member/login";
	}
	
	@Override
	@PostMapping("login")
	public String login(String id, String pwd,Model model,HttpSession session) {
		boolean result = service.login(id,pwd);
		if(result) {
		session.setAttribute("userId",id);
		model.addAttribute("message","로그인이 완료되었습니다");
		model.addAttribute("redirectUrl","/");
		}
		else {
			model.addAttribute("message","로그인이 실패");
			model.addAttribute("redirectUrl","/member/login");
		}
		return "/common/alert";
	}
	

}
