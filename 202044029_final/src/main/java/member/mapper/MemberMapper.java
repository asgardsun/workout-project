package member.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import member.model.MemberVO;

public interface MemberMapper {
	public int insertMember(MemberVO m);
	public int updateMember(MemberVO m);
	public int updateMember_pw(MemberVO m);
	public int deleteMember(String member_id);
	public MemberVO selectMember(String member_id);
	public MemberVO selectMember_id(String member_id);
	public List<MemberVO> selectAllMember();
	public MemberVO selectMember_name(String member_name);
	
}
