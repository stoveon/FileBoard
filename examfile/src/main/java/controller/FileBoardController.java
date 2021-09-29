package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import model.BoardDto;
import model.SearchCommand;
import service.FileDeleteService;
import service.FileEditService;
import service.FileSelectService;
import service.FileWriteService;

@Controller
@RequestMapping(value="/board/")
@SessionAttributes("boardDto")
public class FileBoardController {
	@Autowired
	private FileSelectService selectService;
	@Autowired
	private FileWriteService writeService;
	@Autowired
	private FileEditService editService;
	@Autowired
	private FileDeleteService deleteService;

	@RequestMapping(value="list")
	public String list(SearchCommand search, Model model, SessionStatus session, @RequestParam(name="pageNum", defaultValue = "1") int pageNum) {
		Map<String, Object> tmp = null;
		List<BoardDto> articleList = null;
		System.out.println("search: "+search.toString());
			if(search.getSearchType() != null && search.getSearchBox() == null) {
				model.addAttribute("msg-noneWord", "검색어를 입력하세요.");
				return "redirect:/board/list";
			}else if(search.getSearchType() != null && search.getSearchBox() != null){
				tmp = selectService.search(pageNum, search);
				articleList = (List<BoardDto>) tmp.get("articleList");
		}else {
			tmp = selectService.list(pageNum);
			articleList = (List<BoardDto>) tmp.get("articleList");
		}
		
		System.out.println(articleList);
		int startNum = (Integer) tmp.get("startNum");
		int endNum = (Integer) tmp.get("endNum");
		int startPaging = (Integer) tmp.get("startPaging");
		int endPaging = (Integer) tmp.get("endPaging");
		int pageBlock = (Integer) tmp.get("pageBlock");
		int totalCount = (Integer) tmp.get("totalCount");
		
		model.addAttribute("articleList", articleList);
		model.addAttribute("startNum", startNum);
		model.addAttribute("endNum", endNum);
		model.addAttribute("startPaging", startPaging);
		model.addAttribute("endPaging", endPaging);
		model.addAttribute("pageBlock", pageBlock);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("search", search);
		return "list";
	}
	
	@RequestMapping(value="detail/{num}")
	public String detail(Model model, @PathVariable int num, String type) {
		BoardDto tmp = null;
		selectService.hitIt(num);
		if(type != null) {
			tmp = selectService.move(num, type);
		} else {
			tmp = selectService.read(num);
		}
		
		String[] str= tmp.getFile().split("_");
		String fileName = "";
		for(int i = 1; i < str.length; i++) {
			if(!((str.length-1) == i)) {
				fileName = str[i] + "_";
			}else {
				fileName += str[i];
			}
		}
		tmp.setFile(fileName);
		model.addAttribute("boardDto", tmp);
		model.addAttribute("maxNum", selectService.maxAndMin().get("MAXNUM"));
		model.addAttribute("minNum", selectService.maxAndMin().get("MINNUM"));
		return "detail";
	}
	
	@RequestMapping(value="fileDown/{num}")
	public String fileDown(@PathVariable int num, Model model) {
		if(num == 0) {
			return "redirect:/board/list";
		}
		boolean check = selectService.fileDown(num);
		if(check == true) {
			model.addAttribute("msg", "C드라이브에 파일이 저장되었습니다.");
		}else if(check == false) {
			model.addAttribute("msg", "파일이 저장되지 않았습니다.");
		}
		
		return "redirect:/board/detail/" + num;
	}
	
	@RequestMapping(value="write", method=RequestMethod.GET)
	public String write(Model model) {
		BoardDto tmp = new BoardDto();
		tmp.setNum(0);
		tmp = writeService.info(tmp);
		model.addAttribute("checkRe", false);
		model.addAttribute("boardDto", tmp);
		return "writeForm";
	}
	
	@RequestMapping(value="write", method=RequestMethod.POST)
	public String write(@RequestParam("fileUp") MultipartFile fileUp, @Valid BoardDto boardDto){
		try {		//이건 서비스에서 던지거나 예외처리하기
			writeService.write(fileUp, boardDto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="write/{num}", method=RequestMethod.GET)
	public String writeRe(BoardDto boardDto, Model model) {
		BoardDto tmp = writeService.info(boardDto);
		model.addAttribute("boardDto", tmp);
		model.addAttribute("checkRe", true);
		return "writeForm";
	}
	
	@RequestMapping(value="write/{num}", method=RequestMethod.POST)
	public String writeRe(@PathVariable("fileUp") MultipartFile fileUp, @Valid BoardDto boardDto) {
		try {
			writeService.reple(boardDto);
			writeService.write(fileUp, boardDto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="update/{num}", method=RequestMethod.GET)
	public String edit(@PathVariable int num, Model model) {
		BoardDto boardDto = selectService.read(num);
		String[] str= boardDto.getFile().split("_");
		String fileName = "";
		for(int i = 1; i < str.length; i++) {
			if(!((str.length-1) == i)) {
				fileName = str[i] + "_";
			}else {
				fileName += str[i];
			}
		}
		boardDto.setFile(fileName);
		model.addAttribute("boardDto", boardDto);
		model.addAttribute("check", false);
		return "updateForm";
	}
	
	@RequestMapping(value="update/{num}", method=RequestMethod.POST)
	public String edit(@Valid @ModelAttribute BoardDto boardDto, 
		String checkPass, SessionStatus sessionStatus, Model model, 
		@RequestParam("fileUp") MultipartFile fileUp) {
		boolean check = false;
		if(fileUp.isEmpty()) {
			check = editService.editExceptFile(boardDto);
		}else {
			check = editService.edit(fileUp, boardDto);
		}
		if(check == true) {
			sessionStatus.setComplete();
			return "redirect:/board/list";
		}else {
			model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
			return "updateForm";
		}
	}
	
	@RequestMapping(value="delete/{num}", method=RequestMethod.GET)
	public String delete(@PathVariable int num, Model model) {
		model.addAttribute("num", num);
		return "deleteForm";
	}
	
	@RequestMapping(value="delete/{num}", method=RequestMethod.POST)
	public String delete(BoardDto boardDto, Model model) {
		if(boardDto.getCheckPass().isEmpty()) {
			model.addAttribute("num", boardDto.getNum());
			model.addAttribute("msg", "비밀번호를 입력하세요.");
			return "redirect:/board/delete/" + boardDto.getNum();
		}
		boolean check = deleteService.delete(boardDto);
		if(!check) {
			model.addAttribute("num", boardDto.getNum());
			model.addAttribute("msg", "다시 입력해주세요.");
			return "redirect:/board/delete/" + boardDto.getNum();
		}
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="fileDel/{num}")
	public String fileDel(BoardDto boardDto, Model model) {
		model.addAttribute("boardDto", boardDto);
		model.addAttribute("check", true);
		return "updateForm";
	}

}
