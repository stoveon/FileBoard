package model;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDto {
	private int rnum;
	private int num;
	
	@NotEmpty(message="작성자를 입력하세요.")
	private String writer;
	@Length(min=2, max=15, message="15자 이내로 작성하세요")
	@NotEmpty(message="제목을 입력하세요.")
	private String title;
	@NotEmpty(message="내용을 입력하세요.")
	private String content;
	private int readcount;
	private int ref;
	private int step;
	private int depth;
	private Timestamp regdate;
	@NotEmpty(message="비밀번호를 입력하세요.")
	private String pass;
	private String file;
	private String checkPass;
	
	public BoardDto() {}
	public BoardDto(int rnum, int num, String writer, String title, String content, int readcount, int ref, int step,
			int depth, Timestamp regdate, String pass, String file) {
		this.rnum = rnum;
		this.num = num;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.readcount = readcount;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.regdate = regdate;
		this.pass = pass;
		this.file = file;
	}
	
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}

	public String getCheckPass() {
		return checkPass;
	}
	public void setCheckPass(String checkPass) {
		this.checkPass = checkPass;
	}
	@Override
	public String toString() {
		return "BoardDto [rnum=" + rnum + ", num=" + num + ", writer=" + writer + ", title=" + title + ", content="
				+ content + ", readcount=" + readcount + ", ref=" + ref + ", step=" + step + ", depth=" + depth
				+ ", regdate=" + regdate + ", pass=" + pass + ", file=" + file + "]";
	}

}
