package member.model;

import java.io.Serializable;

public class MemberAuthVO {
	private static final long serialVersionUID = 1L;
	private String member_id;
	private String member_auth;
	
	public MemberAuthVO() {
	}
	
	public MemberAuthVO(String member_id, String member_auth) {
		super();
		this.member_id = member_id;
		this.member_auth = member_auth;
	}
	
	public String getMember_id() {
		
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_auth() {
		return member_auth;
	}
	public void setMember_auth(String member_auth) {
		this.member_auth = member_auth;
	}

	@Override
	public String toString() {
		return "MemberAuthVO [member_id=" + member_id + ", member_auth=" + member_auth + "]";
	}
	
	
}
