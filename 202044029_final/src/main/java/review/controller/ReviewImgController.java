package review.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/file")
public class ReviewImgController {
//이미지 게시글안에 출력하기

	private static final String FILE_PATH = "c://image//review";
	
	@ResponseBody
	@RequestMapping("/image")
	public byte[] showImage(String filename) {
		//지정된 경로에서 이미지 읽어서 byte[]형태로 반환
				File file = new File(FILE_PATH+filename);
				InputStream in = null;
				
				try {
					in = new FileInputStream(file);
					//스트림을 byte[]형태로 만들기 위해서 라이브러리 추가(commonsIO)
					return IOUtils.toByteArray(in);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					try {
						if(in!=null) in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return null;
	}
}
