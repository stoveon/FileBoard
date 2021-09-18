package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.BoardDao;
import model.BoardDto;

@Service
public class FileDeleteService {
	@Autowired
	private BoardDao boardDao;
	
	public boolean delete(BoardDto boardDto) {
		BoardDto tmp = boardDao.getArticle(boardDto.getNum());
		if(!tmp.getPass().equals(boardDto.getPass())) {
			return false;
		}else {
			boardDao.articleDelete(boardDto.getNum());
			return true;
		}
	}
}
