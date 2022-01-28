package recommend.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import recommend.model.RecommendImgVO;

public class RecommendBoardVO {
//	RECOMM_NUM                      NUMBER(10)     
//	MEMBER_ID              NOT NULL VARCHAR2(50)   
//	RECOMM_TITLE           NOT NULL VARCHAR2(100)  
//	RECOMM_CONTENT                  VARCHAR2(4000) 
//	RECOMM_REG_DATE                 DATE           
//	RECOMM_MODI_DATE                DATE           
//	RECOMM_READ_COUNT               NUMBER(10)     
//	RECOMM_RECOMM_COUNT             NUMBER(10)     
//	RECOMM_DISRECOMM_COUNT          NUMBER(10) 
//	REPLY_COUNT                     NUMBER(10)   
	private int recomm_num;
	private String member_id;
	private String recomm_title;
	private String recomm_content;
	private Date recomm_reg_date;
	private Date recomm_modi_date;
	private int recomm_read_count;
	private int recomm_recomm_count;
	private int recomm_disrecomm_count;
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
	public String getRecomm_title() {
		return recomm_title;
	}
	public void setRecomm_title(String recomm_title) {
		this.recomm_title = recomm_title;
	}
	public String getRecomm_content() {
		return recomm_content;
	}
	public void setRecomm_content(String recomm_content) {
		this.recomm_content = recomm_content;
	}
	public Date getRecomm_reg_date() {
		return recomm_reg_date;
	}
	public void setRecomm_reg_date(Date recomm_reg_date) {
		this.recomm_reg_date = recomm_reg_date;
	}
	public Date getRecomm_modi_date() {
		return recomm_modi_date;
	}
	public void setRecomm_modi_date(Date recomm_modi_date) {
		this.recomm_modi_date = recomm_modi_date;
	}
	public int getRecomm_read_count() {
		return recomm_read_count;
	}
	public void setRecomm_read_count(int recomm_read_count) {
		this.recomm_read_count = recomm_read_count;
	}
	public int getRecomm_recomm_count() {
		return recomm_recomm_count;
	}
	public void setRecomm_recomm_count(int recomm_recomm_count) {
		this.recomm_recomm_count = recomm_recomm_count;
	}
	public int getRecomm_disrecomm_count() {
		return recomm_disrecomm_count;
	}
	public void setRecomm_disrecomm_count(int recomm_disrecomm_count) {
		this.recomm_disrecomm_count = recomm_disrecomm_count;
	}

	@Override
	public String toString() {
		return "RecommendBoardVO [recomm_num=" + recomm_num + ", member_id=" + member_id + ", recomm_title="
				+ recomm_title + ", recomm_content=" + recomm_content + ", recomm_reg_date=" + recomm_reg_date
				+ ", recomm_modi_date=" + recomm_modi_date + ", recomm_read_count=" + recomm_read_count
				+ ", recomm_recomm_count=" + recomm_recomm_count + ", recomm_disrecomm_count=" + recomm_disrecomm_count
				+ "]";
	}
	
	
}
