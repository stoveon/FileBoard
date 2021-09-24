package service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import model.BoardDto;
import model.FileBoardDaoImple;

@Service
public class FileWriteService {
	@Autowired
	private FileBoardDaoImple boardDao;
	
	public boolean write(MultipartFile fileUp, BoardDto boardDto) throws IOException {
		UUID uuid = UUID.randomUUID();
		//중복된 이름의 파일을 저장하지 않기 위해 UUID 키값 생성
		
		String fileName = uuid.toString() + "_" + fileUp.getOriginalFilename();
		
		String filePath = "C:\\board\\FileUpload";
		try {
			File saveFile = new File(filePath);
			
			if(saveFile.exists() == false) {
				saveFile.mkdirs();
			}
			//저장 폴더 없으면 생성
			
			saveFile = new File(filePath + "\\" + fileName);
			fileUp.transferTo(saveFile);
			//전달받은 파일 특정 경로에 특정 파일명으로 저장	
			
			boardDto.setFile(fileName);			
			boardDao.articleInsert(boardDto);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void reple(BoardDto boardDto) {
		BoardDto tmp = boardDao.getArticle(boardDto.getNum());
		boardDao.articleInsertRef(tmp);
	}
	
	public BoardDto info(BoardDto boardDto) {
		BoardDto tmp = new BoardDto();
		if(boardDto.getNum() != 0) {
			tmp = boardDao.getArticle(boardDto.getNum());
			tmp.setStep(tmp.getStep()+1);
			tmp.setDepth(tmp.getDepth()+1);
			tmp.setReadcount(0);
		}else {
			tmp.setRef(boardDao.maxNum()+1);
			tmp.setStep(1);
			tmp.setDepth(0);
			tmp.setReadcount(0);
		}
		return tmp;
	}
}
