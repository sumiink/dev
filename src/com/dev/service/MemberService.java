package com.dev.service;

import java.util.List;

import com.dev.dao.MemberDAO;
import com.dev.vo.MemberVO;

public class MemberService {
	private static MemberService service = new MemberService();
	MemberDAO dao = MemberDAO.getInstance();
	
	private MemberService() {
	}
	public static MemberService getInstance() {
		return service;
	}
	
	//입력, 수정,  삭제, 조회, 리스트
	public void memberInsert(MemberVO member) {
		dao.insertMember(member);
	}
	
	public MemberVO memberSearch(String id) {
		return dao.searchMember(id);
	}
	
	public void memberUpdate(MemberVO member) {
		dao.updateMember(member);
	
	}
	
	public void memberDelete(String id ) {
		dao.deleteMember(id);
	}
	
	public List<MemberVO> memberList(){
		return dao.listMember();
	}
}
