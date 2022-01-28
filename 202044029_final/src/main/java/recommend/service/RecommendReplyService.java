package recommend.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recommend.mapper.RecommendReplyMapper;
import recommend.model.RecommendReplyVO;


@Service
public class RecommendReplyService {
	@Autowired
	private RecommendReplyMapper rrmapper;
	
	public boolean recommendreplyRegister(RecommendReplyVO rr) {
		if(rrmapper.insertRecommend_Reply(rr) > 0) {
			return true;
		}
		return false;
	}
	public boolean recommendreplyModify(RecommendReplyVO rr) {
		if(rrmapper.updateRecommend_Reply(rr) > 0) {
			return true;
		}
		return false;
	}
	public boolean recommendreplysRemoves(int reply_num) {
		if(rrmapper.deleteRecommend_Replys(reply_num) > 0) {
			return true;
		}
		return false;
	}
	public boolean recommendreplyRemove(int num) {
		if(rrmapper.deleteRecommend_Reply(num) > 0) {
			return true;
		}
		return false;
	}
	public RecommendReplyVO recommendreplyGetOne(int num) {
		return rrmapper.selectRecommend_Reply(num);
	}
	public List<RecommendReplyVO> recommendreplyGetAll(int num){
		return rrmapper.selectAllRecommend_Reply(num);
	}
	public int getnReplyCount(int num) {
		return rrmapper.getReplyCnt(num);
	}
	
	public boolean doReplyBlock(String block, int num) {
		if(rrmapper.updateBlock(block, num) > 0) {
			return true;
		}
		return false;
	}
}
