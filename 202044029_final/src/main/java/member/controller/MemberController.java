package member.controller;

import java.security.Principal;
import java.util.ArrayList;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import common.model.EmailDTO;
import common.model.EmailSender;
import member.model.MemberAuthVO;
import member.model.MemberVO;
import member.service.MemberService;
import recommend.service.RecommendBoardService;
import review.service.ReviewBoardService;

@RequestMapping(value="/")
@Controller
public class MemberController {
	
	@Autowired
	private EmailDTO email;
	
	@Autowired
	private EmailSender emailSender;
	
	@Autowired
	private MemberService service;
	
//	@Autowired
//	@Qualifier("authenticationManager")
//	private AuthenticationManager authManager;
	
	@Autowired
	private ReviewBoardService reviewservice;	
	
	@Autowired
	private RecommendBoardService recservice;
	
	@Autowired
	private BCryptPasswordEncoder pwencoder;
	
	//로그인폼
	@RequestMapping("/loginForm")
	public String showLoginForm(Model model,HttpSession session) {
		String state = service.getState();
		session.setAttribute("state", state);
		//model.addAttribute("url", nservice.getURL(state));
		return "member/loginForm";
	}
	
	//회원가입
	@RequestMapping("/joinForm")
	public String showJoinForm() {
		return "member/joinForm";
	}
	
	//회원가입
	@RequestMapping("/join")
	public String showJoin(String id,String password,String name,int gender,String tel1,String tel2,String tel3,
			String email1,String email2,String zipNo,String roadAddrPart1,String addrDetail,Model model,String auth) {
		MemberVO member = new MemberVO();
		//멤버권한 넣기
		List<MemberAuthVO> authList = new ArrayList<MemberAuthVO>();
		authList.add(new MemberAuthVO(id, "ROLE_USER"));
		member.setAuthList(authList);
		//권한제외 멤버정보 넣기
		member.setMember_id(id);
		member.setMember_password(pwencoder.encode(password));
		member.setMember_name(name);
		member.setMember_gender(gender); //0:남자, 1:여자	
		String phoneNum = tel1+"-"+tel2+"-"+tel3;
		member.setMember_phonenum(phoneNum);
		String email = email1+"@"+email2;
		member.setMember_email(email);
		String address = "["+zipNo+"]"+roadAddrPart1+","+addrDetail;
		member.setMember_address(address);
		boolean result = service.memberRegister(member);
		String msg = "다시 입력해주세요.";
		String url = "joinForm";
		if(result) {
			msg = "가입해주셔서 감사합니다.";
			url = "loginForm";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		System.out.println(member);
		return "/member/result";
	}
	
	//주소 api
	@RequestMapping("/jusoPopup")
	public String showAddressForm() {
		return "/member/jusoPopup";
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/myPage")
	public String showMyPage(Principal principal,Model model) {
		String id = principal.getName();
		model.addAttribute("member", service.memberGetOne(id));
		model.addAttribute("review", reviewservice.reviewboardGetIdAll(id));
		model.addAttribute("recommend", recservice.recommendboardGetAll_Id(id));
		return "/member/myPage";
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/modifyForm")
	public String showModifyForm(Model model, Principal principal) {
		String id = principal.getName();
		MemberVO member = service.memberGetOne(id);
		model.addAttribute("member", member);
		return "member/modifyForm";
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/modify")
	public String showModify(String id,String SNSid,String password,String name,String tel1,String tel2,String tel3,
			String email1,String email2,String zipNo,String roadAddrPart1,String addrDetail,Model model){

			MemberVO member = service.memberGetOne(id);
			member.setMember_id(id);			
			member.setMember_password(pwencoder.encode(password));
			member.setMember_phonenum(tel1+"-"+tel2+"-"+tel3);
			member.setMember_email(email1+"@"+email2);
			member.setMember_address("["+zipNo+"]"+roadAddrPart1+","+addrDetail);
			service.memberModify(member);
			model.addAttribute("msg", "수정되었습니다.");
			model.addAttribute("url", "myPage?id="+id);
			return "/member/result";
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/deleteForm")
	public String showDeleteForm(Principal principal,Model model) {
		String id = principal.getName();
		model.addAttribute("id", id);
		return "/member/deleteForm";
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/delete")
	public String showDelete(Model model,String member_id,String password,String snsId,HttpSession session) {
		//네이버 회원
			session.invalidate();
			service.memberRemove(member_id);	
			model.addAttribute("url", "/logout");
			model.addAttribute("msg", "탈퇴되셨습니다.");
			return "/member/result";

	}
	//id체크
	@ResponseBody
	@RequestMapping("/checkId")
	public String showCheckId(String id){
		System.out.println("data:"+id);
		MemberVO member = service.memberGetOne(id);
		if(id.equals("") || id.equals(null)) {
			return "no";
		}else {
			if(member==null) {
				return "true";
			}else {
				return "false";
			}
		}	
	}
	
	
	//findid폼 페이지
	@RequestMapping("/findidForm")
	public String showFindIdForm() {
		return "/member/findidForm";
	}
	
	//findid
	@RequestMapping("/findId")
	public String showFindId(String member_name,String member_email,Model model) {
		MemberVO member = service.memberFindId(member_name);
		String url = "findidForm";
		String msg = "입력하신 정보로 가입 된 회원 아이디는 존재하지 않습니다.";
		if(member != null) {
			if(member_email.equals(member.getMember_email())) {
				model.addAttribute("member_id", member.getMember_id());
				return "redirect:/findidForm?findResult=success";
			}
		}
		model.addAttribute("url", url);
		model.addAttribute("msg", msg);
		return "/member/result";			
	}
	
	//findpw 폼 페이지
	@RequestMapping("/findpwForm")
	public String showFindpwForm() {
		return "/member/findpwForm";
	}
	
	@RequestMapping("/findPw")
	public String showFindpw(String member_id,String member_name,String member_email,Model model) throws Exception {
		MemberVO member = service.memberGetOne(member_id);
		String msg = "일치하는 정보가 없습니다.";
		String url = "findpwForm";
		if(member != null) {
			if(member_name.equals(member.getMember_name())) {
				if(member_email.equals(member.getMember_email())) {
					StringBuffer randomPw = new StringBuffer();
					Random random = new Random();
					for (int i = 0; i < 10; i++) {
					    int rIndex = random.nextInt(3);
					    switch (rIndex) {
					    case 0:
					        // a-z
					    	randomPw.append((char) ((int) (random.nextInt(26)) + 97));
					        break;
					    case 1:
					        // A-Z
					    	randomPw.append((char) ((int) (random.nextInt(26)) + 65));
					        break;
					    case 2:
					        // 0-9
					    	randomPw.append((random.nextInt(10)));
					        break;
					    }
					}
					String password = randomPw.toString();
					member.setMember_password(pwencoder.encode(password));
					service.memberPwModify(member);
					email.setContent("임시 비밀번호는 "+ randomPw +" 입니다.");
					email.setReceiver(member.getMember_email());
					email.setSubject("화랑 임시 비밀번호를 안내해드립니다.");
					emailSender.SendEmail(email);
					msg = "가입 시 등록한 메일주소 "+member.getMember_email()+" 비밀번호가 발송되었습니다.";
					url = "loginForm";
				}
			}
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "/member/result";
	}
	
}
