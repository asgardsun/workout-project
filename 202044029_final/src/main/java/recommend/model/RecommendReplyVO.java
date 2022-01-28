package recommend.model;

import java.util.Date;

public class RecommendReplyVO {
//	RECOMM_REPLY_NUM                NUMBER(10)     
//	RECOMM_NUM             NOT NULL NUMBER(10)     
//	MEMBER_ID              NOT NULL VARCHAR2(50)   
//	RECOMM_REPLY_CONTENT   NOT NULL VARCHAR2(2000) 
//	RECOMM_REPLY_REG_DATE           DATE           
//	RECOMM_REPLY_MODI_DATE          DATE 
	private int recomm_reply_num;
	private int recomm_num;
	private String member_id;
	private String recomm_reply_content;
	private String recomm_reply_reg_date;
	private Date recomm_reply_modi_date;
	private String block;
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	
	public int getRecomm_reply_num() {
		return recomm_reply_num;
	}
	public void setRecomm_reply_num(int recomm_reply_num) {
		this.recomm_reply_num = recomm_reply_num;
	}
	public int getRecomm_num() {
		return recomm_num;
	}
	public void setRecomm_num(int recomm_num) {
		this.recomm_num = recomm_num;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getRecomm_reply_content() {
		return recomm_reply_content;
	}
	public void setRecomm_reply_content(String recomm_reply_content) {
		this.recomm_reply_content = recomm_reply_content;
	}
	public String getRecomm_reply_reg_date() {
		return recomm_reply_reg_date;
	}
	public void setRecomm_reply_reg_date(String recomm_reply_reg_date) {
		this.recomm_reply_reg_date = recomm_reply_reg_date;
	}
	public Date getRecomm_reply_modi_date() {
		return recomm_reply_modi_date;
	}
	public void setRecomm_reply_modi_date(Date recomm_reply_modi_date) {
		this.recomm_reply_modi_date = recomm_reply_modi_date;
	}
	@Override
	public String toString() {
		return "Recommend_ReplyVO [recomm_reply_num=" + recomm_reply_num + ", recomm_num=" + recomm_num + ", member_id="
				+ member_id + ", recomm_reply_content=" + recomm_reply_content + ", recomm_reply_reg_date="
				+ recomm_reply_reg_date + ", recomm_reply_modi_date=" + recomm_reply_modi_date + "]";
	}
	
}
