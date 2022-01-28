package member.model;

import java.util.Date;
import java.util.List;

public class MemberVO {
//    member_id varchar2(50) PRIMARY KEY,
//    member_name varchar2(50) NOT NULL,
//    member_password varchar2(60) not null,
//    member_email varchar2(60),
//    member_gender number not null,
//    member_phonenum varchar2(50),
//    member_address varchar2(200),
//    member_reg_date date DEFAULT sysdate,
//    member_status varchar2(20) default 'useable',  
//    member_point number(10) default 0
	private String member_id;
	private String member_name;
	private String member_password;
	private String member_email;
	private int member_gender;	
	private String member_phonenum;
	private String member_address;
	private Date member_reg_date;
	private String member_status;
	private int member_point;
	
	private List<MemberAuthVO> authList;
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_password() {
		return member_password;
	}
	public void setMember_password(String member_password) {
		this.member_password = member_password;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public int getMember_gender() {
		return member_gender;
	}
	public void setMember_gender(int member_gender) {
		this.member_gender = member_gender;
	}
	public String getMember_phonenum() {
		return member_phonenum;
	}
	public void setMember_phonenum(String member_phonenum) {
		this.member_phonenum = member_phonenum;
	}
	public String getMember_address() {
		return member_address;
	}
	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}
	public Date getMember_reg_date() {
		return member_reg_date;
	}
	public void setMember_reg_date(Date member_reg_date) {
		this.member_reg_date = member_reg_date;
	}
	public String getMember_status() {
		return member_status;
	}
	public void setMember_status(String member_status) {
		this.member_status = member_status;
	}
	public int getMember_point() {
		return member_point;
	}
	public void setMember_point(int member_point) {
		this.member_point = member_point;
	}
	
	public List<MemberAuthVO> getAuthList() {
		return authList;
	}
	public void setAuthList(List<MemberAuthVO> authList) {
		this.authList = authList;
	}
	@Override
	public String toString() {
		return "MemberVO [member_id=" + member_id + ", member_name=" + member_name + ", member_password="
				+ member_password + ", member_email=" + member_email + ", member_gender=" + member_gender
				+ ", member_phonenum=" + member_phonenum + ", member_address=" + member_address + ", member_reg_date="
				+ member_reg_date + ", member_status=" + member_status + ", member_point=" + member_point + "]";
	}
	
	
	
}
