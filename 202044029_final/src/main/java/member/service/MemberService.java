package member.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import member.mapper.MemberAuthMapper;
import member.mapper.MemberMapper;
import member.model.MemberAuthVO;
import member.model.MemberVO;

@Service
public class MemberService {
	@Autowired
	private MemberMapper membermapper;
	
	
	@Autowired
	private MemberAuthMapper maMapper;
	
	
	// CSRF(요청위조) 방지를 위한 애플리케이션과 사용자 간의 상태를 보유하는 고유한 세션 토큰 생성
	// 상태 토큰(=세션토큰)은 추후 검증을 위해 세션에 저장되어야 한다.
	public String getState() {
		// 상태 토큰으로 사용할 랜덤 문자열 생성(고유한 세션토큰을 위함)
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		// 세션 또는 별도의 저장 공간에 상태 토큰을 저장
		return state;
	}
	public MemberVO memberFindId(String member_name) {
		return membermapper.selectMember_name(member_name);
	}
	
	@Transactional
	public boolean memberRegister(MemberVO member) {
		if(membermapper.insertMember(member)>0) {
			MemberAuthVO ma = member.getAuthList().get(0);
			if(maMapper.insertMember_Auth(ma)>0) {
				return true;				
			}
		}
		return false;
	}
	public boolean memberModify(MemberVO m) {
		if(membermapper.updateMember(m) > 0) {
			return true;
		}
		return false;
	}
	public boolean memberPwModify(MemberVO m) {
		if(membermapper.updateMember_pw(m) > 0) {
			return true;
		}
		return false;
	}
	public boolean memberRemove(String id) {
		if(membermapper.deleteMember(id) > 0) {
			MemberAuthVO auth = maMapper.selectMember_Auth(id);
			maMapper.deleteMember_Auth(auth);
			return true;
		}
		return false;
	}
	public MemberVO memberGetOne(String id) {
		return membermapper.selectMember(id);
	}
	public List<MemberVO> memberGetAll(){
		return membermapper.selectAllMember();
	}
	public boolean memberCheckPw(String id, String pw) {
		MemberVO member = membermapper.selectMember(id);
		if(member != null) {
			if(pw.equals(member.getMember_password())) {
				return true;
			}
		}
		return false;
	}
}
