package review.model;

import java.util.Date;

public class ReviewReplyVO {
//	REVIEW_REPLY_NUM                NUMBER(10)     
//	REVIEW_NUM             NOT NULL NUMBER(10)     
//	MEMBER_ID              NOT NULL VARCHAR2(50)   
//	REVIEW_REPLY_CONTENT   NOT NULL VARCHAR2(2000) 
//	REVIEW_REPLY_REG_DATE           DATE           
//	REVIEW_REPLY_MODI_DATE          DATE    
	private int review_reply_num;
	private int review_num;
	private String member_id;
	private String review_reply_content;
	private String review_reply_reg_date;
	private Date review_reply_modi_date;
	/* 신고처리 -hj */
	private String block;
	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	@Override
	public String toString() {
		return "Review_ReplyVO [review_reply_num=" + review_reply_num + ", review_num=" + review_num + ", member_id="
				+ member_id + ", review_reply_content=" + review_reply_content + ", review_reply_reg_date="
				+ review_reply_reg_date + ", review_reply_modi_date=" + review_reply_modi_date + "]";
	}
	
	public String getReview_reply_reg_date() {
		return review_reply_reg_date;
	}

	public void setReview_reply_reg_date(String review_reply_reg_date) {
		this.review_reply_reg_date = review_reply_reg_date;
	}

	public int getReview_reply_num() {
		return review_reply_num;
	}
	public void setReview_reply_num(int review_reply_num) {
		this.review_reply_num = review_reply_num;
	}
	public int getReview_num() {
		return review_num;
	}
	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getReview_reply_content() {
		return review_reply_content;
	}
	public void setReview_reply_content(String review_reply_content) {
		this.review_reply_content = review_reply_content;
	}
	public Date getReview_reply_modi_date() {
		return review_reply_modi_date;
	}
	public void setReview_reply_modi_date(Date review_reply_modi_date) {
		this.review_reply_modi_date = review_reply_modi_date;
	}
	
}
