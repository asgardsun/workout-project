package recommend.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recommend.mapper.RecommendImgMapper;
import recommend.model.RecommendImgVO;

@Service
public class RecommendImgService {
	@Autowired
	private RecommendImgMapper rimapper;
	
	public boolean recommendimgRegister(RecommendImgVO ri) {
		if(rimapper.insertRecomm_Img(ri) > 0) {
			return true;
		}
		return false;
	}
	public boolean recommendimgModify(RecommendImgVO ri) {
		if(rimapper.updateRecomm_Img(ri) > 0 ) {
			return true;
		}
		return false;
	}
	public boolean recommendimgUuidRemove(String uuid) {
		if(rimapper.deleteRecomm_Img_uuid(uuid) > 0) {
			return true;
		}
		return false;
	}
	public boolean recommendimgNumRemove(int num) {
		if(rimapper.deleteRecomm_Img_num(num) > 0) {
			return true;
		}
		return false;
	}
	public List<RecommendImgVO> recommendimgGetOne(int num) {
		return rimapper.selectRecomm_Img_num(num);
	}
	public List<RecommendImgVO> recommendimgGetAll(){
		return rimapper.selectAllRecomm_Img();
	}
}
