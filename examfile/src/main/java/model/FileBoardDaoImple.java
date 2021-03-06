package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FileBoardDaoImple implements BoardDao{
	private static SqlSessionTemplate sqlSessionTemplate;
	
	private FileBoardDaoImple(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
		System.out.println("FileBoardDao(SqlSessionTemplate)");
	}
	@Override
	public int getArticleCount() {
		return sqlSessionTemplate.selectOne("count");
	}

	@Override
	public List<BoardDto> getArticles(int startNum, int endNum) {
		Map<String, Integer> tmp = new HashMap<String, Integer>();
		tmp.put("startNum", startNum);
		tmp.put("endNum", endNum);
		return sqlSessionTemplate.selectList("articles", tmp);
	}

	@Override
	public BoardDto getArticle(int num) {
		return sqlSessionTemplate.selectOne("detail", num);
	}

	@Override
	public BoardDto getNextArticle(int num) {
		int nextNum = sqlSessionTemplate.selectOne("nextNum", num);
		return sqlSessionTemplate.selectOne("detail", nextNum);
	}

	@Override
	public BoardDto getAgoArticle(int num) {
		int agoNum = sqlSessionTemplate.selectOne("agoNum", num);
		return sqlSessionTemplate.selectOne("detail", agoNum);
	}

	@Override
	public void articleInsert(BoardDto article) {
		sqlSessionTemplate.insert("insert", article);
	}

	@Override
	public void articleInsertRef(BoardDto article) {
		Map<String, Integer> tmp = new HashMap<String, Integer>();
		tmp.put("ref", article.getRef());
		tmp.put("step", article.getStep());
		sqlSessionTemplate.update("reply", tmp);
	}
	
	@Override
	public void articleUpdate(BoardDto article) {
		sqlSessionTemplate.update("update", article);
	}
	
	@Override
	public void articleDelete(int num) {
		sqlSessionTemplate.delete("delete", num);
	}

	@Override
	public void articeCounterPlus(int num) {
		sqlSessionTemplate.update("counterPlus", num);
	}
	@Override
	public int maxNum() {
		return sqlSessionTemplate.selectOne("maxNum");
	}
	@Override
	public int minNum() {
		return sqlSessionTemplate.selectOne("minNum");
	}
	@Override
	public Map<String, Integer> maxAndMin() {
		return sqlSessionTemplate.selectOne("maxAndMin");
	}
	@Override
	public void updateExceptFile(BoardDto article) {
		sqlSessionTemplate.update("updateExceptFile", article);
	}
//	@Override
//	public List<BoardDto> search(String searchType, String searchBox) {
//		Map<String, String> tmp = new HashMap<String, String>();
//		tmp.put("type", searchType);
//		tmp.put("search", searchBox);
//		return sqlSessionTemplate.selectList("search", tmp);
//	}
	@Override
	public List<BoardDto> searchTotal(Map<String, Object> searchCmd) {
		return sqlSessionTemplate.selectList("searchTotal", searchCmd);
	}
	@Override
	public List<BoardDto> search2(Map<String, Object> searchCmd) {
		return sqlSessionTemplate.selectList("search2", searchCmd);
	}
	@Override
	public int searchCount(Map<String, Object> searchCmd) {
		return sqlSessionTemplate.selectOne("searchCount", searchCmd);
	}
	@Override
	public int searchTotalCount(Map<String, Object> searchCmd) {
		return sqlSessionTemplate.selectOne("searchTotalCount", searchCmd);
	}
	
}