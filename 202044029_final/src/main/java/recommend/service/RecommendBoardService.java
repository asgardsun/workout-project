package recommend.service;

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
import org.springframework.web.servlet.View;

import common.model.CriteriaDTO;
import common.model.DownloadView;
import recommend.mapper.RecommendBoardMapper;
import recommend.mapper.RecommendImgMapper;
import recommend.mapper.RecommendReplyMapper;
import recommend.model.RecommendBoardVO;
import recommend.model.RecommendImgVO;


@Service
public class RecommendBoardService {
	//파일 저장할 경로
	private static final String UPLOAD_PATH="C:\\IMAGE\\recommend";
	@Autowired
	private RecommendBoardMapper rbmapper;
	@Autowired
	private RecommendImgMapper rimapper;
	@Autowired
	private RecommendReplyMapper rrmapper;

	//paging
	//페이지 리스트
	public List<RecommendBoardVO> pagingList(CriteriaDTO cri){
		return rbmapper.getListWithPaging(cri);
	}
	//총 페이지 수
	public int getTotalCount(CriteriaDTO cri) {
		return rbmapper.getTotalCount(cri);
	}
	//main화면에 띄울 5개 게시글
	public List<RecommendBoardVO> selectRecentFree(){
		return rbmapper.selectRecentFree();
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
			copyList.put("recomm_uuid", fullName);
			copyList.put("recomm_upload_path", UPLOAD_PATH);
			copyList.put("recomm_filename", originName);
			targetList.add(copyList);
		}
		return targetList;
	}
	
	//파일 다운로드
	public DownloadView getAttachment(String uuid) {
		RecommendImgVO recommImg = rimapper.selectRecomm_Img_uuid(uuid);
		String fileName = recommImg.getRecomm_uuid();
		File file = new File(UPLOAD_PATH, fileName);
		DownloadView view = new DownloadView(file);
		return view;
	}
	//파일 등록 요청
	public boolean recommimgRegister(RecommendBoardVO rb,List<MultipartFile> fileList) {
		if(rbmapper.insertRecommend_Board(rb) > 0) {
			if(fileList.get(0).getOriginalFilename().equals("")) {
				System.out.println("파일 선택하지 않음");
				return true;
			}else {
				System.out.println("선택fileList" + fileList.size());
				List<Map<String, Object>> fList = writeFile(fileList);
				System.out.println(fList.toString());
				for (int i = 0; i < fList.size(); i++) {
					Map<String, Object> fileMap = fList.get(i);
					fileMap.put("recomm_num", rb.getRecomm_num());
					RecommendImgVO recommImg = new RecommendImgVO();
					recommImg.setRecomm_uuid((String) fileMap.get("recomm_uuid"));
					recommImg.setRecomm_num((int) fileMap.get("recomm_num"));
					recommImg.setRecomm_upload_path((String) fileMap.get("recomm_upload_path"));
					recommImg.setRecomm_filename((String) fileMap.get("recomm_filename"));
					if (rimapper.insertRecomm_Img(recommImg) > 0) {
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
	public boolean recommimgModify(RecommendBoardVO rb,List<MultipartFile> fileList) {
		if(rbmapper.updateRecommend_Board(rb) > 0 ) {
			if(fileList.get(0).getOriginalFilename().equals("")) {
				System.out.println("파일을 선택하지 않음");
				return true;				
			}else {
				System.out.println("선택fileList" + fileList.size());
				List<Map<String, Object>> fList = writeFile(fileList);
				for (int i = 0; i < fList.size(); i++) {
					Map<String, Object> fileMap = fList.get(i);
					fileMap.put("recomm_num", rb.getRecomm_num());
					RecommendImgVO recommImg = new RecommendImgVO();
					recommImg.setRecomm_uuid((String) fileMap.get("recomm_uuid"));
					recommImg.setRecomm_num((int) fileMap.get("recomm_num"));
					recommImg.setRecomm_upload_path((String) fileMap.get("recomm_upload_path"));
					recommImg.setRecomm_filename((String) fileMap.get("recomm_filename"));
					if (rimapper.insertRecomm_Img(recommImg) > 0) {
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
	public boolean recommboardRegister_files(RecommendBoardVO rb, List<MultipartFile> fileList) {
		if(rbmapper.insertRecommend_Board(rb) > 0) {
			if(fileList.get(0).getOriginalFilename().equals("")) {
				System.out.println(fileList.size());
				System.out.println("파일선택하지 않음");
				return true;
			}else {
				System.out.println("fileList : " + fileList.size());
				List<Map<String, Object>> fList = writeFile(fileList);
				System.out.println(fList.toString());
				for (int i = 0; i < fList.size(); i++) {
					Map<String, Object> fileMap = fList.get(i);
					fileMap.put("recomm_num", rb.getRecomm_num());
					RecommendImgVO recommImg = new RecommendImgVO();
					recommImg.setRecomm_uuid((String) fileMap.get("recomm_uuid"));
					recommImg.setRecomm_num((int) fileMap.get("recomm_num"));
					recommImg.setRecomm_upload_path((String) fileMap.get("recomm_upload_path"));
					recommImg.setRecomm_filename((String) fileMap.get("recomm_filename"));
					if (rimapper.insertRecomm_Img(recommImg) > 0) {
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
	public boolean recommboardImageInsert(int recommNum,MultipartFile file) {
		String fullName = writeFile(file);
		RecommendImgVO ri = new RecommendImgVO();
		ri.setRecomm_num(recommNum);
		ri.setRecomm_filename(file.getOriginalFilename());				
		ri.setRecomm_uuid(fullName);
		ri.setRecomm_upload_path(UPLOAD_PATH);
		
		if(rimapper.insertRecomm_Img(ri) > 0) {
			return true;
		}
		return false;	
	}
		
	public boolean recommendboardRemove(int num) {
		if(rbmapper.deleteRecommend_Board(num) > 0) {
			rimapper.deleteRecomm_Img_num(num);
			rrmapper.deleteRecommend_Replys(num);
			return true;
		}
		return false;
	}
	public RecommendBoardVO recommendboardGetOne(int num) {
		return rbmapper.selectRecommend_Board(num);
	}
	public List<RecommendBoardVO> recommendboardGetAll_Id(String id){
		return rbmapper.selectRecommend_Board_Id(id);
	}
	public List<RecommendBoardVO> recommendboardGetAll(){
		return rbmapper.selectAllRecommend_Board();
	}
	//조회수
	public RecommendBoardVO increasReadCnt(int num){
		RecommendBoardVO rb = null;
		if(rbmapper.updateRecommend_Count(num) > 0) {
			rb = rbmapper.selectRecommend_Board(num);
		}
		return rb;
	}
	
}
