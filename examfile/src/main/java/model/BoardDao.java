package model;

import java.util.List;
import java.util.Map;

public interface BoardDao {
	public int getArticleCount();
	public List<BoardDto> getArticles(int startNum, int endNum);
	public BoardDto getArticle(int num);
	public BoardDto getNextArticle(int num);
	public BoardDto getAgoArticle(int num);
	public void articleInsert(BoardDto article);
	public void articleInsertRef(BoardDto article);
	public void articleUpdate(BoardDto article);
	public void articleDelete(int num);
	public void articeCounterPlus(int num);
	public int maxNum();
	public int minNum();
	public Map<String, Integer> maxAndMin();
	public void updateExceptFile(BoardDto article);
//	public List<BoardDto> search(String searchType, String searchBox);
//	public List<BoardDto> searchTotal(String searchBox);
	public List<BoardDto> search2(Map<String, Object> search);
	public int searchCount(Map<String, Object> search);
}
