package recommend.controller;

import java.security.Principal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import recommend.model.RecommendReplyVO;
import recommend.service.RecommendReplyService;



@Controller
@RequestMapping("/recommendreply")
public class RecommendReplyController {
	@Autowired
	private RecommendReplyService service;

	@ResponseBody
	@RequestMapping("/rwrite")
	public boolean showRRegister(RecommendReplyVO recommendReply) {
		return service.recommendreplyRegister(recommendReply);
	}
	
	@ResponseBody
	@RequestMapping("/replyView")
	public List<RecommendReplyVO> showReplyView(int num) {
		List<RecommendReplyVO> replyList = service.recommendreplyGetAll(num);
		return replyList;
	}
	@ResponseBody
	@RequestMapping("/rmodify")
	public boolean showRmodify(int num,String content) {
		RecommendReplyVO rr = service.recommendreplyGetOne(num);
		rr.setRecomm_reply_content(content);
		service.recommendreplyModify(rr);
		return true;
	}
	@ResponseBody
	@RequestMapping("/rdelete")
	public boolean showRdelete(int num){
		service.recommendreplyRemove(num);
		return true;
	}
}
