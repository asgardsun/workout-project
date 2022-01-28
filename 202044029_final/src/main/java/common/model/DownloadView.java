package common.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {
	//View를 구현하기 위한 클래스 생성
	private File file;
	public DownloadView(File file) {
		this.file = file;
		//응답객체(response)의 header(ContentType 다운로드로 변경)
		setContentType("application/download;utf-8");
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentLengthLong(file.length());
		
		String fullName = file.getName();
		int idx = fullName.lastIndexOf("_")+1;
		String fileName = fullName.substring(idx);
		fileName = URLEncoder.encode(fileName, "utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream out = response.getOutputStream();
		FileInputStream in = null;
		in = new FileInputStream(file);
		FileCopyUtils.copy(in, out);
		if(in != null) 
			in.close();
		out.flush();
	}

}