package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BoardDao {
	public int getArticleCount();
	public List<BoardDto> getArticles(int startNum, int endNum);
	public BoardDto getArticle(int num);
	public BoardDto getNextArticle(int num);
	public BoardDto getAgoArticle(int num);
	public void articleInsert(BoardDto article);
	public void artticleInsertRef(BoardDto article);
	public void articleUpdate(BoardDto article);
	public void articleDelete(int num);
	public void articeCounterPlus(int num);
}
