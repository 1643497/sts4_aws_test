package com.test.pro04.common.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalModelAttribute {
	@ModelAttribute("userId")
	public String userId(HttpSession session) {
		return (String) session.getAttribute("userId");
	}
}
