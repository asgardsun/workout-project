package recommend.controller;

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
import recommend.model.RecommendBoardVO;
import recommend.service.RecommendBoardService;
import recommend.service.RecommendImgService;
import recommend.service.RecommendReplyService;

@Controller
@RequestMapping("/recommend")
public class RecommendBoardController {
	@Autowired
	private RecommendBoardService rbservice;
	@Autowired
	private RecommendImgService riservice;
	@Autowired
	private RecommendReplyService rrservice;
	@Autowired
	private MemberService mservice;
	
	@RequestMapping(value = "/recommendboard",method = RequestMethod.GET)
	public String showRecommendBoardForm(Model model,CriteriaDTO cri,Principal principal) {
		PageDTO page = new PageDTO(cri, rbservice.getTotalCount(cri));
		model.addAttribute("pageMaker", page);
		model.addAttribute("recommendList", rbservice.pagingList(cri));
		model.addAttribute("principal", principal);
		return "/recommend/recommendboardForm";
	}
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String showreviewwrite(Principal principal,Model model) {
		model.addAttribute("principal", principal);
		return "/recommend/recommendwriteForm";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String showrRegister(RecommendBoardVO recommBoard,
			 Model model,MultipartHttpServletRequest request){
		// exh_name : ????????? ???????????? ???
		List<MultipartFile> fileList =request.getFiles("file");
		String msg = "";
		String url = "";

//		boolean result = service.reviewboardRegister_file(rb, file);
		boolean result = rbservice.recommboardRegister_files(recommBoard, fileList);
		if (result) {
			msg = "?????????????????????.";
			url = "view?num=" + recommBoard.getRecomm_num();
		} else {
			msg = "?????? ??????????????????.";
			url = "write";
		}
		model.addAttribute("recommend", rbservice.recommendboardGetOne(recommBoard.getRecomm_num()));
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "/recommend/result";
	}
	
	@RequestMapping("/view")
	public String showview(Model model, int num,Principal principal) {
		String id = principal.getName();
		model.addAttribute("id", id);
		model.addAttribute("recomm", rbservice.increasReadCnt(num));
		model.addAttribute("imgs", riservice.recommendimgGetOne(num));
		return "/recommend/recommend";
	}
	@RequestMapping("/remove")
	public String showrecommendRemove(int num,Model model) {
		rbservice.recommendboardRemove(num);
		model.addAttribute("msg", "?????????????????????.");
		model.addAttribute("url", "recommendboard");
		return "/recommend/result";			
	}
	// ???????????? ???????????? ???????????? ???????????? ??????????????? ??????
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String showreviewmodify(Model model, int num) {
		model.addAttribute("recomm", rbservice.recommendboardGetOne(num));
		model.addAttribute("imgs", riservice.recommendimgGetOne(num));
		return "/recommend/recommendmodifyForm";
	}

	// ???????????? ??????????????? ?????????????
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String showmodify(MultipartHttpServletRequest request,String id, Model model, int num, String title, String content) {
		List<MultipartFile> fileList = request.getFiles("file");
		RecommendBoardVO recomm = rbservice.recommendboardGetOne(num);
		recomm.setRecomm_title(title);
		recomm.setRecomm_content(content);

		boolean result = rbservice.recommimgModify(recomm,fileList);
		System.out.println(result);
		
		String msg = "?????? ??????????????????.";
		String url = "modify?num="+num;
		if (result) {
			msg = "?????????????????????.";
			url = "view?num=" + num;
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "/recommend/result";
	}

	//????????? ????????????(?????????)
    //?????? ??????????????? ??????
	public static final String UPLOAD_PATH = "c:\\image\\recommend";
	
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
		return rbservice.getAttachment(uuid);
	}
	
	//???????????? ???????????? ?????? ?????? ?????? ?????? ?????? ????????? ??????
	@ResponseBody
	@RequestMapping(value="/fileDelete", method=RequestMethod.POST)
	public boolean doFileDelete(String uuid) {
		return riservice.recommendimgUuidRemove(uuid);
	}
}
