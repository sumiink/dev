package com.dev.service;

import com.dev.dao.MemberDAO;
import com.dev.vo.MemberVO;

public class MemberService {
	private static MemberService service = new MemberService();
	private MemberService() {
	}
	public static MemberService getInstance() {
		return service;
	}
	
	//입력, 수정,  삭제, 조회, 리스트
	public void memberInsert(MemberVO member) {
		MemberDAO dao = MemberDAO.getInstance();
		dao.insertMember(member);
	}
	
	public MemberVO memberSearch() {
		return null;
	}
	
	public void memberUpdate() {
		
	
	}
	
	public void memberDelete() {
		
	}
}
