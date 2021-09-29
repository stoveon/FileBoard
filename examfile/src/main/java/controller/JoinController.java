package controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import model.AgreeCommand;
import model.JoinCommand;

@Controller
@RequestMapping(value="/board/")
public class JoinController {
	private ModelAndView mv = null;
	
	@RequestMapping(value="join", method = RequestMethod.GET)
	public ModelAndView join() {
		mv = new ModelAndView();
		mv.setViewName("join/agreeTerms");
		return mv;
	}
	
	@RequestMapping(value="join", method = RequestMethod.POST)
	public ModelAndView join(AgreeCommand agreeCommand) {
		ModelAndView mv = new ModelAndView();
		System.out.println(agreeCommand);
		if(agreeCommand.essential() == false) {
			mv.addObject("essentialMsg", agreeCommand.essential());
			mv.setViewName("redirect:/board/join");
		}else {
			mv.addObject("agreeCmd", agreeCommand);
			mv.setViewName("join/certification");
		}
		System.out.println(agreeCommand.essential());
		return mv;
	}
	
	@RequestMapping(value="join2", method = RequestMethod.GET)
	public ModelAndView joinInformation() {
		mv = new ModelAndView();
		mv.setViewName("redirect:/board/join");
		return mv;
	}
	
	@RequestMapping(value="join2", method = RequestMethod.POST)
	public ModelAndView joinInformation(ModelAndView mv, @Valid JoinCommand joinCommand) {
		
		mv.setViewName("join/complete");
		return mv;
	}
}
