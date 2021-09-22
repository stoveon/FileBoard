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
	public void artticleInsertRef(BoardDto article) {
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
	public int maxRef() {
		return sqlSessionTemplate.selectOne("maxRef");
	}

}