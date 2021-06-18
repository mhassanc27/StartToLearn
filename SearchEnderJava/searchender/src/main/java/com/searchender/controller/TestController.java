package com.searchender.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.searchender.Model.Test;
import com.searchender.service.TestService;

@Controller
public class TestController {
	@Autowired
	private TestService testService;

	@RequestMapping("/test")
	public String index(){
		return "test";
	}
	@RequestMapping("/testController")
	public String getDetailsTraditionally(HttpServletRequest request){
		String userName=request.getParameter("userName");
		String userPassword=request.getParameter("userPassword");
		System.out.println("userName: "+userName+"  ||  "+ "userPassword: "+userPassword);
		
		return "";
	}
	
	@RequestMapping(path="/testControllerOneByOneModel", method=RequestMethod.GET)
	public String getDetailsOneByOneBySpringApproach(
			@RequestParam("userName")String userName,
			@RequestParam("userPassword")String userPassword,
			Model model){
		System.out.println("Trd ->"+"userName: "+userName+"  ||  "+ "userPassword: "+userPassword);
		
		Test test=new Test();
		test.setUserName(userName);
		test.setUserPassword(userPassword);
		System.out.println(test);
		
		model.addAttribute("test", test);
		
		
		return "success";
	}
	
	@RequestMapping(path="/testControllerOneByOneModelAndView", method=RequestMethod.GET)
	public ModelAndView about(
			@RequestParam("userName") String userName,
			@RequestParam("userPassword") String userPassword){
		
		Test test=new Test();
		test.setUserName(userName);
		test.setUserPassword(userPassword);
		System.out.println(test);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("test", test);
		modelAndView.setViewName("success");
		return modelAndView;
	}
	
	@RequestMapping(path="/testControllerTogetherModel")
	public String getDetailsTogetherBySpringApproach(
			@ModelAttribute("test")Test test,
			Model model){
		
		int id=testService.saveTest(test);
		System.out.println(test+"  test saved in db with the id "+id);
		return "success";
	}
}
