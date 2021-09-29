package model;

public class SearchCommand {

	private String searchType;
	private String searchBox;
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchBox() {
		return searchBox;
	}
	public void setSearchBox(String searchBox) {
		this.searchBox = searchBox;
	}
	@Override
	public String toString() {
		return "SearchCommand [searchType=" + searchType + ", searchBox=" + searchBox + "]";
	}
	
}
