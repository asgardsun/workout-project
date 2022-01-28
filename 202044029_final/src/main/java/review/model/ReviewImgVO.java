package review.model;

public class ReviewImgVO {
//	REVIEW_UUID        NOT NULL VARCHAR2(200) 
//	REVIEW_NUM         NOT NULL NUMBER(10)    
//	REVIEW_UPLOAD_PATH NOT NULL VARCHAR2(100) 
//	REVIEW_FILENAME    NOT NULL VARCHAR2(200) 
	private String review_uuid;
	private int review_num;
	private String review_upload_path;
	private String review_filename;
	
	
	public String getReview_uuid() {
		return review_uuid;
	}
	public void setReview_uuid(String review_uuid) {
		this.review_uuid = review_uuid;
	}
	public int getReview_num() {
		return review_num;
	}
	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}
	public String getReview_upload_path() {
		return review_upload_path;
	}
	public void setReview_upload_path(String review_upload_path) {
		this.review_upload_path = review_upload_path;
	}
	public String getReview_filename() {
		return review_filename;
	}
	public void setReview_filename(String review_filename) {
		this.review_filename = review_filename;
	}
	@Override
	public String toString() {
		return "Review_ImgVO [review_uuid=" + review_uuid + ", review_num=" + review_num + ", review_upload_path="
				+ review_upload_path + ", review_filename=" + review_filename + "]";
	}
	
}
