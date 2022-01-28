package review.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.swing.text.View;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import review.mapper.ReviewImgMapper;
import review.model.ReviewImgVO;


@Service
public class ReviewImgService {
	@Autowired
	private ReviewImgMapper rimapper;

	public boolean reviewimgUuidRemove(String uuid) {
		if(rimapper.deleteReview_Img_uuid(uuid) > 0) {
			return true;
		}
		return false;
	}
	public boolean reviewimgNumRemove(int num) {
		if(rimapper.deleteReview_Img_num(num) > 0) {
			return true;
		}
		return false;
	}
	public int reviewimgListCount(int num) {
		int count = rimapper.selectReview_ImgList_Count(num);
		return count;
	}
	public List<ReviewImgVO> reviewimgGetImgList(int num){
		return rimapper.selectReview_Img_num(num);
	}
	public ReviewImgVO reviewimgGetOne(String uuid) {
		return rimapper.selectReview_Img_uuid(uuid);
	}
	public List<ReviewImgVO> reviewimgGetAll(){
		return rimapper.selectAllReview_Img();
	}
}
