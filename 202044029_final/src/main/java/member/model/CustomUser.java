package member.model;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomUser extends User {

	private static final long serialVersionUID = 1L;
	private MemberVO member;

	public CustomUser(String member_id, String member_password, Collection<? extends GrantedAuthority> authorities) {
		super(member_id, member_password, authorities);
		// TODO Auto-generated constructor stub
	}

	public CustomUser(MemberVO vo) {
		super(vo.getMember_id(), vo.getMember_password(), vo.getAuthList().stream()
				.map(member_auth -> new SimpleGrantedAuthority(member_auth.getMember_auth())).collect(Collectors.toList()));
		this.member = vo;
	}

}
