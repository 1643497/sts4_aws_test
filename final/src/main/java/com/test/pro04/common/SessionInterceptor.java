package com.test.pro04.common;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SessionInterceptor implements HandlerInterceptor{
	 @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        // 세션에서 userId 가져오기
	        String userId = (String) request.getSession().getAttribute("userId");

	        // 세션 정보가 없으면 로그인 페이지로 리다이렉트
	        if (userId == null) {
	            response.sendRedirect("/member/login");  // 로그인 페이지로 리다이렉트
	            return false; // 요청을 더 이상 처리하지 않음
	        }
	        return true; // 계속해서 요청을 처리하도록 허용
	    }
}
