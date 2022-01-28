package review.mapper;

import java.util.List;

import review.model.ReviewImgVO;

public interface ReviewImgMapper {
	public int insertReview_Img(ReviewImgVO ri);
	public int updateReview_Img(ReviewImgVO ri);
	
	public int deleteReview_Img_uuid(String review_uuid);
	public int deleteReview_Img_num(int recomm_num);
	
	public List<ReviewImgVO> selectAllReview_Img();
	public List<ReviewImgVO> selectReview_Img_num(int review_num);
	public ReviewImgVO selectReview_Img_uuid(String uuid);
	
	public int selectReview_ImgList_Count(int review_num);
}
