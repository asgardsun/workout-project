package review.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;

import common.model.CriteriaDTO;
import common.model.PageDTO;
import member.service.MemberService;
import net.coobird.thumbnailator.Thumbnails;
import review.model.ReviewBoardVO;
import review.service.ReviewBoardService;
import review.service.ReviewImgService;
import review.service.ReviewReplyService;



@Controller
@RequestMapping("/review")
public class ReviewBoardController {
	@Autowired
	private MemberService memberservice;
	@Autowired
	private ReviewBoardService service;
	@Autowired
	private ReviewImgService imgservice;
	@Autowired
	private ReviewReplyService replyservice;

	@RequestMapping(value = "/reviewboard",method = RequestMethod.GET)
	public String showreviewboard(Model model,CriteriaDTO cri,Principal principal) {
		System.out.println("showreviewboard principal : " + principal.getName());
		System.out.println("showreviewboard principal : " + principal.toString());
		
		PageDTO page = new PageDTO(cri, service.getTotalCount(cri));
		model.addAttribute("pageMaker", page);
		model.addAttribute("reviewList", service.pagingList(cri));
		model.addAttribute("principal", principal);
		return "/review/reviewboardForm";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String showreviewwrite(Principal principal,Model model) {
		model.addAttribute("principal", principal);
		return "/review/reviewwriteForm";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String showrRegister(ReviewBoardVO reviewBoard,
			 Model model,MultipartHttpServletRequest request){
		// exh_name : ????????? ???????????? ???
		List<MultipartFile> fileList =request.getFiles("file");
		String msg = "";
		String url = "";

//		boolean result = service.reviewboardRegister_file(rb, file);
		boolean result = service.reviewboardRegister_files(reviewBoard, fileList);
		if (result) {
			msg = "?????????????????????.";
			url = "view?num=" + reviewBoard.getReview_num();
		} else {
			msg = "?????? ??????????????????.";
			url = "write";
		}
		model.addAttribute("review", service.reviewboardGetOne(reviewBoard.getReview_num()));
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "/review/result";
	}

	@RequestMapping("/view")
	public String showreview(Model model, int num,Principal principal) {
		String id = principal.getName();
		model.addAttribute("id", id);
		model.addAttribute("review", service.increasReadCnt(num));
		model.addAttribute("imgs", imgservice.reviewimgGetImgList(num));
		return "/review/review";
	}
	@RequestMapping("/remove")
	public String showreviewremove(int num,Model model) {
		service.reviewboardRemove(num);
		model.addAttribute("msg", "?????????????????????.");
		model.addAttribute("url", "reviewboard");
		return "/review/result";
	}
	// ???????????? ???????????? ???????????? ???????????? ??????????????? ??????
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String showreviewmodify(Model model, int num) {
		model.addAttribute("review", service.reviewboardGetOne(num));
		model.addAttribute("imgs", imgservice.reviewimgGetImgList(num));
		return "/review/reviewmodifyForm";
	}

	// ???????????? ??????????????? ?????????????
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String showmodify(MultipartHttpServletRequest request,String id, Model model, int num, String title, String content) {
		List<MultipartFile> fileList = request.getFiles("file");
		ReviewBoardVO review = service.reviewboardGetOne(num);
		review.setReview_title(title);
		review.setReview_content(content);

		boolean result = service.reviewimgModify(review,fileList);
		System.out.println(result);
		
		String msg = "?????? ??????????????????.";
		String url = "modify?num="+num;
		if (result) {
			msg = "?????????????????????.";
			url = "view?num=" + num;
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "/review/result";
	}

	//????????? ????????????(?????????)
    //?????? ??????????????? ??????
	public static final String UPLOAD_PATH = "c:\\image\\review";

	@RequestMapping("/downloadThumb")
	protected void download(String uuid, HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		String filePath = UPLOAD_PATH + "\\" + uuid;
		File image = new File(filePath);
		int lastIndex = uuid.lastIndexOf(".");
		String fileName = uuid.substring(0,lastIndex);
		File thumbnail = new File(UPLOAD_PATH+"\\"+"thumbnail"+"\\"+fileName+".png");
		if (image.exists()) { 
			thumbnail.getParentFile().mkdirs();
			Thumbnails.of(image).size(640, 480).outputFormat("png").toFile(thumbnail);
		}
		
		FileInputStream in = new FileInputStream(thumbnail);
		byte[] buffer = new byte[1024 * 8];
		while (true) {
			int count = in.read(buffer); // ????????? ???????????? ????????????
			if (count == -1) // ????????? ???????????? ??????????????? ??????
				break;
			out.write(buffer, 0, count);
		}
		in.close();
		out.close();
	}
	
	//?????? ????????????(???????????? ?????? ??? ???????????????)
	@RequestMapping("/download")
	public View download(String uuid) {
		System.out.println("??????uuid : "+uuid);
		return service.getAttachment(uuid);
	}
	
	//???????????? ???????????? ?????? ?????? ?????? ?????? ?????? ????????? ??????
	@ResponseBody
	@RequestMapping(value="/fileDelete", method=RequestMethod.POST)
	public boolean doFileDelete(String uuid) {
		return imgservice.reviewimgUuidRemove(uuid);
	}

}
