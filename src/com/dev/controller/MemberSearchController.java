package com.dev.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.service.MemberService;
import com.dev.vo.MemberVO;

public class MemberSearchController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한건조회 (id) -> 결과 반환.
		// MemberService -> MemberDAO ()
		
		String id = request.getParameter("id");
		String job = request.getParameter("job");
		String path = null;
		
	
		if(job.equals("search")) {
			path = "result/memberSearchOutput.jsp";
		}else if(job.equals("update")){
			path = "memberUpdate.jsp";
		}else if(job.equals("delete")) {
			path = "memberDelete.jsp";
		}
		
		MemberService service = MemberService.getInstance();
		MemberVO member = service.memberSearch(id);
		
		request.setAttribute("member",  member);
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
