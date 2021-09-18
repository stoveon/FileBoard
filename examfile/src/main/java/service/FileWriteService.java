package service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import model.BoardDto;
import model.FileBoardDaoImple;

@Service
public class FileWriteService {
	@Autowired
	private FileBoardDaoImple boardDao;
	
	public boolean write(MultipartFile file, BoardDto boardDto) throws IOException {
		String fileName = file.getOriginalFilename();
		String filePath = "c:\\board\\fileUpload";
		FileOutputStream fos = null;
		try {
//			File saveFile = new File(filePath + fileName);
//			file.transferTo(saveFile);
			
			byte[] fileBuffer = file.getBytes();
			//MultipartFile의 데이터를 바이트 배열로 추출
			
			fos = new FileOutputStream(filePath + fileName);
			fos.write(fileBuffer);
			//FileOutputStream 클래스의 write()로 파일을 filePath에 저장
			
			boardDao.articleInsert(boardDto);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(fos != null) {
				fos.close();
			}
		}
		return false;
	}
	
	public void reple(int num) {
		BoardDto tmp = boardDao.getArticle(num);
		boardDao.artticleInsertRef(tmp);
	}
}
