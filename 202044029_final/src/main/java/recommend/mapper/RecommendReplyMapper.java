package recommend.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import recommend.model.RecommendReplyVO;



public interface RecommendReplyMapper {
	public int insertRecommend_Reply(RecommendReplyVO rr);
	public int updateRecommend_Reply(RecommendReplyVO rr);
	public int deleteRecommend_Reply(int recomm_reply_num);
	public int deleteRecommend_Replys(int review_num);
	public RecommendReplyVO selectRecommend_Reply(int recomm_reply_num);
	public List<RecommendReplyVO> selectAllRecommend_Reply(int recomm_num);
	//댓글 수 조회
	public int getReplyCnt(int recomm_num);
	//댓글 총 개수
	public int getTotalCount();
	
	//댓글  block 처리
	public int updateBlock(@Param("block")String block, @Param("num") int num);
}
