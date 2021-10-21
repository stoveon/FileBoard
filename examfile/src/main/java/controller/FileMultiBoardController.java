package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import service.MultiWriteService;

@Controller
@RequestMapping(value="/multi/")
public class FileMultiBoardController {
	@Autowired
	private MultiWriteService writeService;
	
	@RequestMapping(value="multiUpload", method = RequestMethod.GET)
	public String upload() {
		return "/multi/writeForm";
	}
	
	@RequestMapping(value="multiUpload", method = RequestMethod.POST)
	public String upload(String title, String content, MultipartHttpServletRequest mpfile, Model model) {
		List<String> upFiles = writeService.fileSave(title, content, mpfile);
		model.addAttribute("title", title);
		model.addAttribute("content", content);
		model.addAttribute("upFiles", upFiles);
		return "/multi/checkForm";
	}
}
