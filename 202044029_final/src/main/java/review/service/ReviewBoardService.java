package review.service;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import common.model.CriteriaDTO;
import common.model.DownloadView;
import review.mapper.ReviewBoardMapper;
import review.mapper.ReviewImgMapper;
import review.mapper.ReviewReplyMapper;
import review.model.ReviewBoardVO;
import review.model.ReviewImgVO;



@Service
public class ReviewBoardService {
	//파일 저장할 경로
	private static final String UPLOAD_PATH = "C:\\IMAGE\\review";
	@Autowired
	private ReviewBoardMapper rbmapper;
	@Autowired
	private ReviewImgMapper rimapper;
	@Autowired
	private ReviewReplyMapper rrmapper;
	
	//관람자 후기
	public List<ReviewBoardVO> selectExhReview(String exh_name){
		return rbmapper.selectExhReview(exh_name);
	}
	
	//paging
	
	//페이지 리스트
	public List<ReviewBoardVO> pagingList(CriteriaDTO cri){
		return rbmapper.getListWithPaging(cri);
	}
	//총 페이지 수
	public int getTotalCount(CriteriaDTO cri) {
		return rbmapper.getTotalCount(cri);
	}
	

	//파일업로드를 위한 파일 읽기,파일이름변환(첨부파일 업로드)
	public String writeFile(MultipartFile file) {
		String fullName = null;
		UUID uuid = UUID.randomUUID();
		fullName = uuid.toString()+"_"+file.getOriginalFilename();
		File target = new File(UPLOAD_PATH,fullName);
		try {
			FileCopyUtils.copy(file.getBytes(), target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File Copy Error");
			return null;
		}
		return fullName;
	}

	//파일 복사 저장하는 메소드
		public List<Map<String, Object>> writeFile(List<MultipartFile> fileList) {
			List<Map<String, Object>> targetList = new ArrayList<Map<String,Object>>();
			for(int i=0; i < fileList.size(); i++) {
				MultipartFile file = fileList.get(i);
				String originName = file.getOriginalFilename();
				String fullName = null;
				UUID uuid = UUID.randomUUID();
				fullName = i+"_"+uuid.toString()+"_"+originName;
				File target = new File(UPLOAD_PATH, fullName);
				try {
					FileCopyUtils.copy(file.getBytes(), target);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("파일 복사 예외 발생");
					return null;
				}
				Map<String, Object> copyList = new HashMap<String, Object>();
				copyList.put("review_uuid", fullName);
				copyList.put("review_upload_path", UPLOAD_PATH);
				copyList.put("review_filename", originName);
				targetList.add(copyList);
			}
			return targetList;
		}
		
		//파일 다운로드
		public DownloadView getAttachment(String uuid) {
			ReviewImgVO reviewImg = rimapper.selectReview_Img_uuid(uuid);
			String fileName = reviewImg.getReview_uuid();
			File file = new File(UPLOAD_PATH, fileName);
			DownloadView view = new DownloadView(file);
			return view;
		}
		//파일 등록 요청
		public boolean reviewimgRegister(ReviewBoardVO rb,List<MultipartFile> fileList) {
			if(rbmapper.insertReview_Board(rb) > 0) {
				if(fileList.get(0).getOriginalFilename().equals("")) {
					System.out.println("파일 선택하지 않음");
					return true;
				}else {
					System.out.println("선택fileList" + fileList.size());
					List<Map<String, Object>> fList = writeFile(fileList);
					System.out.println(fList.toString());
					for (int i = 0; i < fList.size(); i++) {
						Map<String, Object> fileMap = fList.get(i);
						fileMap.put("review", rb.getReview_num());
						ReviewImgVO reviewImg = new ReviewImgVO();
						reviewImg.setReview_uuid((String) fileMap.get("review_uuid"));
						reviewImg.setReview_num((int) fileMap.get("review_num"));
						reviewImg.setReview_upload_path((String) fileMap.get("review_upload_path"));
						reviewImg.setReview_filename((String) fileMap.get("review_filename"));
						if (rimapper.insertReview_Img(reviewImg) > 0) {
							System.out.println("파일 DB등록 성공");
						} else {
							System.out.println("파일 DB등록 실패");
						}
					}
				}
				return true;
			}
			return false;
		}
		//파일 수정 요청
		public boolean reviewimgModify(ReviewBoardVO rb,List<MultipartFile> fileList) {
			if(rbmapper.updateReview_Board(rb) > 0 ) {
				if(fileList.get(0).getOriginalFilename().equals("")) {
					System.out.println("파일을 선택하지 않음");
					return true;				
				}else {
				System.out.println("선택fileList" + fileList.size());
				List<Map<String, Object>> fList = writeFile(fileList);
				for (int i = 0; i < fList.size(); i++) {
					Map<String, Object> fileMap = fList.get(i);
					fileMap.put("review_num", rb.getReview_num());
					ReviewImgVO reviewImg = new ReviewImgVO();
					reviewImg.setReview_uuid((String) fileMap.get("review_uuid"));
					reviewImg.setReview_num((int) fileMap.get("review_num"));
					reviewImg.setReview_upload_path((String) fileMap.get("review_upload_path"));
					reviewImg.setReview_filename((String) fileMap.get("review_filename"));
					if (rimapper.insertReview_Img(reviewImg) > 0) {
						System.out.println("파일 DB등록 성공");
					} else {
						System.out.println("파일 DB등록 실패");
					}
				}
				return true;
				}
			}
			return false;
		}
	//게시글안에 첨부파일 리스트와 같이 작성하기
	public boolean reviewboardRegister_files(ReviewBoardVO rb, List<MultipartFile> fileList) {
		if(rbmapper.insertReview_Board(rb) > 0) {
			if(fileList.get(0).getOriginalFilename().equals("")) {
				System.out.println("파일선택하지 않음");
				return true;
			}else {
				System.out.println("fileList : " + fileList.size());
				List<Map<String, Object>> fList = writeFile(fileList);
				System.out.println(fList.toString());
				for (int i = 0; i < fList.size(); i++) {
					Map<String, Object> fileMap = fList.get(i);
					fileMap.put("review_num", rb.getReview_num());
					ReviewImgVO reviewImg = new ReviewImgVO();
					reviewImg.setReview_uuid((String) fileMap.get("review_uuid"));
					reviewImg.setReview_num((int) fileMap.get("review_num"));
					reviewImg.setReview_upload_path((String) fileMap.get("review_upload_path"));
					reviewImg.setReview_filename((String) fileMap.get("review_filename"));
					if (rimapper.insertReview_Img(reviewImg) > 0) {
						System.out.println("파일 DB등록 성공");
					} else {
						System.out.println("파일 DB등록 실패");
					}
				}

				return true;

			}
		}
		return false;
	}

	//첨부파일 저장
	public boolean reviewboardImageInsert(int reviewNum,MultipartFile file) {
		String fullName = writeFile(file);
		ReviewImgVO ri = new ReviewImgVO();
		ri.setReview_num(reviewNum);
		ri.setReview_filename(file.getOriginalFilename());				
		ri.setReview_uuid(fullName);
		ri.setReview_upload_path(UPLOAD_PATH);

		if(rimapper.insertReview_Img(ri) > 0) {
			return true;
		}
		return false;	
	}
	//게시글 삭제
	public boolean reviewboardRemove(int num) {
		if(rbmapper.deleteReview_Board(num) > 0) {
			rimapper.deleteReview_Img_num(num);
			rrmapper.deleteReview_Replys(num);
			return true;
		}
		return false;
	}
	//num의 게시글 찾기
	public ReviewBoardVO reviewboardGetOne(int num) {
		return rbmapper.selectReview_Board(num);
	}
	//유저가 쓴 게시글 목록
	public List<ReviewBoardVO> reviewboardGetIdAll(String id){
		return rbmapper.selectReview_Board_Id(id);
	}
	//게시글 목록
	public List<ReviewBoardVO> reviewboardGetAll(){
		return rbmapper.selectAllReview_Board();
	}
	//리뷰상세보면 조회수 증가
	public ReviewBoardVO increasReadCnt(int num) {
		ReviewBoardVO rb = null;
		if(rbmapper.updateReview_count(num) > 0) {
			rb = rbmapper.selectReview_Board(num);
		}
		return rb;
	}
}
