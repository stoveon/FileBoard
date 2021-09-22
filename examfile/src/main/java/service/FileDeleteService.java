package service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import model.BoardDao;
import model.BoardDto;

@Service
public class FileDeleteService {
	@Autowired
	private BoardDao boardDao;
	
	public boolean delete(BoardDto boardDto) {
		BoardDto tmp = boardDao.getArticle(boardDto.getNum());
		if(!tmp.getPass().equals(boardDto.getCheckPass())) {
			return false;
		}else {
			String realPath = "C:\\board\\FileUpload\\";
			String fileName = tmp.getFile();
			File saveFile = new File(realPath + fileName);
			if(saveFile.exists()) {
				saveFile.delete();
			}
			boardDao.articleDelete(boardDto.getNum());
			return true;
		}
	}
}
