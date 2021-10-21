package controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import model.AgreeCommand;
import model.JoinCommand;

@Controller
@RequestMapping(value="/board/")
public class JoinController {
	
	@RequestMapping(value="join", method = RequestMethod.GET)
	public ModelAndView join(ModelAndView mv) {
		System.out.println("join.get");
		mv = new ModelAndView();
		mv.setViewName("join/agreeTerms");
		return mv;
	}
	
	@RequestMapping(value="join", method = RequestMethod.POST)
	public ModelAndView join(AgreeCommand agreeCommand) {
		System.out.println("join.post");
		ModelAndView mv = new ModelAndView();
		System.out.println(agreeCommand);
		if(agreeCommand.essential() == false) {
			mv.addObject("essentialMsg", agreeCommand.essential());
			mv.setViewName("redirect:/board/join");
		}else {
			mv.addObject("agreeCmd", agreeCommand);
			mv.addObject("joinCommand", new JoinCommand());
			mv.setViewName("join/certification");
		}
		System.out.println("essential: " + agreeCommand.essential());
		return mv;
	}
	
	@RequestMapping(value="join2", method = RequestMethod.GET)
	public ModelAndView joinInformation(ModelAndView mv) {
		System.out.println("join2.get");
		if(AgreeCommand.essential() == false) {
		mv.addObject("essentialMsg", AgreeCommand.essential());
		mv.setViewName("redirect:/board/join");
		}
		return mv;
	}
	
	@RequestMapping(value="join2", method = RequestMethod.POST)
	public ModelAndView joinInformation(ModelAndView mv, 
			@ModelAttribute("joinCommand") @Valid JoinCommand joinCommand, BindingResult bindingResult) {
		System.out.println("join2.post");
		if(joinCommand.passCheck() == false) {
			
		}
		mv.setViewName("join/joinComplete");
		return mv;
	}
}
