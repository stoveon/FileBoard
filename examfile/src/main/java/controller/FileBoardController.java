package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import model.BoardDto;
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
	public String list(Model model, HttpServletRequest request) {
		String pageNum = request.getParameter("pageNum");
		int currentPage = 0;
		if(pageNum == null) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(pageNum);
		}
		Map<String, Object> tmp = selectService.list(currentPage);
		List<BoardDto> articleList = (List<BoardDto>) tmp.get("articleList");
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

		return "list";
	}
	
//	@RequestMapping(value="detailF/{num}", method=RequestMethod.GET)
//	public String detail(@PathVariable int num, Model model) {
//		model.addAttribute("num", num);
//		model.addAttribute("boardDto", new BoardDto());
//		return "/exfile/detail";
//	}
	
	@RequestMapping(value="detail/{num}")
	public String detail(Model model, @PathVariable int num, String type) {
		BoardDto tmp = null;
		System.out.println(num + " / " + type);
		if(type != null) {
			tmp = selectService.move(num, type);
		}else {
			tmp = selectService.read(num);
		}
//		System.out.println(tmp.getFile());
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
//		System.out.println(tmp.getFile());
		model.addAttribute("boardDto", tmp);
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
		tmp.setRef(1);
		tmp.setStep(0);
		tmp.setDepth(0);
		model.addAttribute("boardDto", tmp);
		return "writeForm";
	}
	
	@RequestMapping(value="write", method=RequestMethod.POST)
	public String write(@RequestParam("file") MultipartFile file, @Valid BoardDto boardDto, BindingResult bindingResult, Model model) throws IOException {
		if(boardDto.getNum() != 0) {
			writeService.reple(file, boardDto.getNum());
		}
		System.out.println("num : " + boardDto.getNum());
		System.out.println("file " + file);
//		if(bindingResult.hasErrors()) {
//			return "writeForm";
//		}
		writeService.write(file, boardDto);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="update/{num}", method=RequestMethod.GET)
	public String edit(@PathVariable int num, Model model) {
		BoardDto boardDto = selectService.read(num);
		model.addAttribute("boardDto", boardDto);
		model.addAttribute("check", false);
		return "updateForm";
	}
	
	@RequestMapping(value="update/{num}", method=RequestMethod.POST)
	public String edit(@Valid @ModelAttribute BoardDto boardDto, 
			BindingResult bindingResult, String checkPass,
			SessionStatus sessionStatus, Model model) {
		if(bindingResult.hasErrors()) {
			return "updateForm";
		}else {
			BoardDto tmp = boardDto;
			tmp.setPass(checkPass);
			boolean check = editService.edit(tmp);
			if(check == true) {
				sessionStatus.setComplete();
				return "redirect:/board/list";
			}else {
				model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
				return "updateForm";
			}
		}
	}
	
	@RequestMapping(value="delete/{num}", method=RequestMethod.GET)
	public String delete(@PathVariable int num, Model model) {
		model.addAttribute("num", num);
		return "deleteForm";
	}
	
	@RequestMapping(value="delete/{num}", method=RequestMethod.POST)
	public String delete(BoardDto boardDto, Model model) {
//		System.out.println(boardDto.getNum());
//		System.out.println(boardDto.getCheckPass());
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
	public String fileDel(@PathVariable int num, Model model) {
		model.addAttribute("check", true);
		return "redirect:/board/update";
	}

}
