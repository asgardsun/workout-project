package review.model;

import java.util.Date;
import java.util.List;

public class ReviewBoardVO {
//	REVIEW_NUM                      NUMBER(10)     
//	MEMBER_ID              NOT NULL VARCHAR2(50)   
//	REVIEW_TITLE           NOT NULL VARCHAR2(100)  
//	REVIEW_EXH_NAME        NOT NULL VARCHAR2(200)  
//	REVIEW_CONTENT                  VARCHAR2(4000) 
//	REVIEW_REG_DATE                 DATE           
//	REVIEW_MODI_DATE                DATE           
//	REVIEW_READ_COUNT               NUMBER(10)     
//	REVIEW_RECOMM_COUNT             NUMBER(10)     
//	REVIEW_DISRECOMM_COUNT          NUMBER(10)  
//	REPLY_COUNT                     NUMBER(10)   
	private int review_num;
	private String member_id;
	private String review_title;
	private String review_exh_name;
	private String review_content;
	private Date review_reg_date;
	private Date review_modi_date;
	private int review_read_count;
	private int review_recomm_count;
	private int review_disrecomm_count;
	private int reply_count;
	private String block;
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	
	public int getReply_count() {
		return reply_count;
	}
	public void setReply_count(int reply_count) {
		this.reply_count = reply_count;
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
	public String getReview_title() {
		return review_title;
	}
	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}
	public String getReview_exh_name() {
		return review_exh_name;
	}
	public void setReview_exh_name(String review_exh_name) {
		this.review_exh_name = review_exh_name;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public Date getReview_reg_date() {
		return review_reg_date;
	}
	public void setReview_reg_date(Date review_reg_date) {
		this.review_reg_date = review_reg_date;
	}
	public Date getReview_modi_date() {
		return review_modi_date;
	}
	public void setReview_modi_date(Date review_modi_date) {
		this.review_modi_date = review_modi_date;
	}
	public int getReview_read_count() {
		return review_read_count;
	}
	public void setReview_read_count(int review_read_count) {
		this.review_read_count = review_read_count;
	}
	public int getReview_recomm_count() {
		return review_recomm_count;
	}
	public void setReview_recomm_count(int review_recomm_count) {
		this.review_recomm_count = review_recomm_count;
	}
	public int getReview_disrecomm_count() {
		return review_disrecomm_count;
	}
	public void setReview_disrecomm_count(int review_disrecomm_count) {
		this.review_disrecomm_count = review_disrecomm_count;
	}
	@Override
	public String toString() {
		return "Review_BoardVO [review_num=" + review_num + ", member_id=" + member_id + ", review_title="
				+ review_title + ", review_exh_name=" + review_exh_name + ", review_content=" + review_content
				+ ", review_reg_date=" + review_reg_date + ", review_modi_date=" + review_modi_date
				+ ", review_read_count=" + review_read_count + ", review_recomm_count=" + review_recomm_count
				+ ", review_disrecomm_count=" + review_disrecomm_count + "]";
	}
	
	
}
