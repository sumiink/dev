package com.dev.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 전체 어플리케이션의 컨트롤러 등록 관리
 * .do, .action 
 */

@WebServlet("/")
public class FrontController extends HttpServlet {
	HashMap<String, Controller> list;
	String charset = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		charset = config.getInitParameter("charset"); 
		
		
		// front요청하면 제일처음 한번만 시행되는 메서드
		list = new HashMap<>();
		System.out.println("front init()");
//		list.put("요청페이지.do", "컨트롤러");
		list.put("/memberInsert.do", new MemberInsertController());
	}
	
	@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(charset);
		
		String uri = req.getRequestURI();			//  /dev/insertMember.do
		String contextPath = req.getContextPath();	// /dev/insertMember.do
		//insertMember.do
		String path =uri.substring(contextPath.length()); 	
		System.out.println(path);
		
		Controller sub = list.get(path);	//controller
		sub.execute(req, resp);
	}
}
