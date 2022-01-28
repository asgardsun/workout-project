package recommend.mapper;

import java.util.List;

import recommend.model.RecommendImgVO;



public interface RecommendImgMapper {
	public int insertRecomm_Img(RecommendImgVO ri);
	public int updateRecomm_Img(RecommendImgVO ri);
	
	public int deleteRecomm_Img_uuid(String recomm_uuid);
	public int deleteRecomm_Img_num(int recomm_num);
	
	public List<RecommendImgVO> selectAllRecomm_Img();
	public List<RecommendImgVO> selectRecomm_Img_num(int recomm_num);
	public RecommendImgVO selectRecomm_Img_uuid(String uuid);
	
	public int selectRecomm_ImgList_Count(int recomm_num);
	
	
}	
