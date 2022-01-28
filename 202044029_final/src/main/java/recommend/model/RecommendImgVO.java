package recommend.model;

public class RecommendImgVO {
//	RECOMM_UUID        NOT NULL VARCHAR2(200) 
//	RECOMM_NUM         NOT NULL NUMBER(10)    
//	RECOMM_UPLOAD_PATH NOT NULL VARCHAR2(100) 
//	RECOMM_FILENAME    NOT NULL VARCHAR2(200) 
	private String recomm_uuid;
	private int recomm_num;
	private String recomm_upload_path;
	private String recomm_filename;
	
	public String getRecomm_uuid() {
		return recomm_uuid;
	}
	public void setRecomm_uuid(String recomm_uuid) {
		this.recomm_uuid = recomm_uuid;
	}
	public int getRecomm_num() {
		return recomm_num;
	}
	public void setRecomm_num(int recomm_num) {
		this.recomm_num = recomm_num;
	}
	public String getRecomm_upload_path() {
		return recomm_upload_path;
	}
	public void setRecomm_upload_path(String recomm_upload_path) {
		this.recomm_upload_path = recomm_upload_path;
	}
	public String getRecomm_filename() {
		return recomm_filename;
	}
	public void setRecomm_filename(String recomm_filename) {
		this.recomm_filename = recomm_filename;
	}
	@Override
	public String toString() {
		return "Recomm_ImgVO [recomm_uuid=" + recomm_uuid + ", recomm_num=" + recomm_num + ", recomm_upload_path="
				+ recomm_upload_path + ", recomm_filename=" + recomm_filename + "]";
	}
	
}
