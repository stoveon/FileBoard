package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.BoardDto;
import model.FileBoardDaoImple;

@Service
public class FileSelectService {
	@Autowired
	private FileBoardDaoImple boardDao;
	
	public Map<String, Object> list(int currentPage){
		int totalCount = boardDao.getArticleCount();
		int pageBlock = 5;
		int startNum = (currentPage -1) * pageBlock + 1;
		int endNum = startNum + pageBlock-1;
		int startPaging = 1;
		int endPaging = (int) Math.ceil((double)totalCount / 5);
		
		if(totalCount > 0) {
			startPaging = 1;
		}
		
		List<BoardDto> articleList = null;
		if(totalCount > 0) {
			articleList = boardDao.getArticles(startNum, endNum);
		}else {
			articleList = Collections.emptyList();
		}

		Map<String, Object> list = new HashMap<String, Object>();
		list.put("articleList", articleList);
		list.put("startNum", startNum);
		list.put("endNum", endNum);
		list.put("startPaging", startPaging);
		list.put("endPaging", endPaging);
		list.put("pageBlock", pageBlock);
		list.put("totalCount", totalCount);
		
		return list;
	}
	
	public BoardDto read(int num) {
		boardDao.articeCounterPlus(num);
		return boardDao.getArticle(num);
	}
	
	public BoardDto move(int num, String type) {
		BoardDto tmp = null;
		boardDao.articeCounterPlus(num);
		tmp = boardDao.getArticle(num);
		if(type != null) {
			if(type.equals("before")) {
				tmp = boardDao.getAgoArticle(num);
				boardDao.articeCounterPlus(tmp.getNum());
			}else if(type.equals("after")) {
				tmp = boardDao.getNextArticle(num);
				boardDao.articeCounterPlus(tmp.getNum());
			}
		}
		return tmp;
	}
	
	public boolean fileDown(int num) {
		BoardDto tmp = boardDao.getArticle(num);
		String fileName = tmp.getFile();
		System.out.println(tmp.getFile());
		String realPath = "C:\\board\\FileUpload\\";
		
		File file = new File(realPath + fileName);
		
		if(!file.exists()) {	//파일이 존재하지 않는다면
			throw new RuntimeException("file not found");
		}
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			String[] str= fileName.split("_");
			String downFile = "";
			for(int i = 1; i < str.length; i++) {
				if(!((str.length-1) == i)) {
					downFile = str[i] + "_";
				}else {
					downFile += str[i];
				}
			}
			
			File saveFile = new File("C:\\board\\FileDownload");
			if(!saveFile.exists()) {
				saveFile.mkdirs();
			}
			
			saveFile = new File("C:\\board\\FileDownload\\" + downFile);
			if(saveFile.exists()) {		//중복파일명 존재하면 변경
				downFile = UUID.randomUUID().toString() + "_" + downFile;
			}
			
			//대상 파일 지정
			fis = new FileInputStream(file);
			//다운로드 받을 파일의 위치 지정
			fos = new FileOutputStream(new File("C:\\board\\FileDownload\\" + downFile));
			
			int readBuffer = 0;
			byte[] buffer = new byte[512];
			while((readBuffer = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, readBuffer);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(fos != null) {fos.close();}
				if(fis != null) {fis.close();}
			}catch(IOException e) {}
		}
		return false;
	}
}