package review.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import review.mapper.ReviewReplyMapper;
import review.model.ReviewReplyVO;


@Service
public class ReviewReplyService {
	@Autowired
	private ReviewReplyMapper rrmapper;
	
	public boolean reviewreplyRegister(ReviewReplyVO rr) {
		if(rrmapper.insertReview_Reply(rr) > 0) {
			return true;
		}
		return false;
	}
	public boolean reviewreplyModify(ReviewReplyVO rr) {
		if(rrmapper.updateReview_Reply(rr) > 0) {
			return true;
		}
		return false;
	}
	public boolean reviewreplysRemoves(int reply_num) {
		if(rrmapper.deleteReview_Replys(reply_num) > 0) {
			return true;
		}
		return false;
	}
	public boolean reviewreplyRemove(int review_reply_num) {
		if(rrmapper.deleteReview_Reply(review_reply_num) > 0) {
			return true;
		}
		return false;
	}
	public ReviewReplyVO reviewreplyGetOne(int reply_num) {
		return rrmapper.selectReview_Reply(reply_num);
	}
	public List<ReviewReplyVO> reviewreplyGetAll(int num){
		return rrmapper.selectAllReview_Reply(num);
	}
	public int getnReplyCount(int num) {
		return rrmapper.getReplyCnt(num);
	}
	
}
