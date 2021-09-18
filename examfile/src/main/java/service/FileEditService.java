package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.BoardDto;
import model.FileBoardDaoImple;

@Service
public class FileEditService {
	@Autowired
	private FileBoardDaoImple boardDao;
	
	public boolean edit(BoardDto boardDto) {
		BoardDto tmp = boardDao.getArticle(boardDto.getNum());
		if(tmp.getPass().equals(boardDto.getPass())) {
			boardDao.articleUpdate(boardDto);
			return true;
		}
		return false;
	}
}
