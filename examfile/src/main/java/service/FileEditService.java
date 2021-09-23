package service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import model.BoardDto;
import model.FileBoardDaoImple;

@Service
public class FileEditService {
	@Autowired
	private FileBoardDaoImple boardDao;
	
	public boolean edit(MultipartFile fileUp, BoardDto boardDto) {
		BoardDto tmp = boardDao.getArticle(boardDto.getNum());
		if(tmp.getPass().equals(boardDto.getCheckPass())) {	
			try {
				String savePath = "C:\\board\\FileUpload";
				
				String agoFile = tmp.getFile();
				File deleteFile = new File(savePath + "\\" + agoFile);
				if(deleteFile.exists()) {
					deleteFile.delete();
				}
				
				UUID uuid = UUID.randomUUID();
				
				String fileName = uuid.toString() + "_" + fileUp.getOriginalFilename();
				File saveFile = new File(savePath + "\\" + fileName);
				fileUp.transferTo(saveFile);
				
				boardDto.setFile(fileName);
				boardDao.articleUpdate(boardDto);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
		return false;
	}
	
	public boolean editExceptFile(BoardDto boardDto) {
		BoardDto tmp = boardDao.getArticle(boardDto.getNum());
		if(tmp.getPass().equals(boardDto.getCheckPass())) {
			boardDao.articleUpdate(boardDto);
			return true;
		}
		return false;
	}
}
