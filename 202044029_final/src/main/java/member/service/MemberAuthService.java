package member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.mapper.MemberAuthMapper;
import member.model.MemberAuthVO;



@Service
public class MemberAuthService {
	
	@Autowired
	private MemberAuthMapper memberauthmapper;
	
	public boolean memberauthRegister(String id, String auth) {

		System.out.println("MemberAuthService : " + id);
		System.out.println("MemberAuthService : " + auth);

		MemberAuthVO ma = new MemberAuthVO();
		ma.setMember_id(id);
		ma.setMember_auth(auth);
		int count = memberauthmapper.insertMember_Auth(ma);
		if (count > 0) {
			return true;
		}
		return false;
	}

	public boolean memberauthModify(MemberAuthVO ma) {
		if (memberauthmapper.updateMamber_Auth(ma) > 0) {
			return true;
		}
		return false;
	}

	public boolean memberauthRemove(MemberAuthVO ma) {
		if (memberauthmapper.deleteMember_Auth(ma) > 0) {
			return true;
		}
		return false;
	}

	public MemberAuthVO memberauthGetOne(String id) {
		return memberauthmapper.selectMember_Auth(id);
	}

	public List<MemberAuthVO> memberauthGetAll() {
		return memberauthmapper.selectAllMember_Auth();
	}

	public List<String> memberAuthsById(String id) {
		return memberauthmapper.selectMemberAuthsById(id);
	}
}
