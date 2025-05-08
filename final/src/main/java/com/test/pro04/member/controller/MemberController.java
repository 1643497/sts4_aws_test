package com.test.pro04.member.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.pro04.member.dto.MemberDTO;

import jakarta.servlet.http.HttpSession;


public interface MemberController {
public String memberList(Model model,HttpSession session);
public String joinMember();
public String joinMember(MemberDTO dto,Model model);
public String detailMember(@RequestParam("id") String id,Model model);
public String updateMember(MemberDTO dto,Model model);
public String deleteMember(@RequestParam("id") String id,Model model);
public String login();
public String login(@RequestParam("id")String id,@RequestParam("pwd")String pwd,Model model,HttpSession session);
}
