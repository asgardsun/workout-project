package member.mapper;

import java.util.List;

import member.model.MemberAuthVO;

public interface MemberAuthMapper {
	public int insertMember_Auth(MemberAuthVO ma);
	public int updateMamber_Auth(MemberAuthVO ma);
	public int deleteMember_Auth(MemberAuthVO ma);
	public MemberAuthVO selectMember_Auth(String member_id);
	public List<MemberAuthVO> selectAllMember_Auth();
	public List<String> selectMemberAuthsById(String member_id);
}
