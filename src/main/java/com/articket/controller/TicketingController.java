package com.articket.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.articket.VO.TicketingVO;
import com.articket.VO.UserVO;
import com.articket.service.TicketingService;

@Controller
public class TicketingController {
	@Autowired
	TicketingService ts;
	
	@RequestMapping(value="/ticketing", method=RequestMethod.GET)
	public ModelAndView ticketing(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVO uvo = (UserVO) session.getAttribute("nickname");
		int id = uvo.getId();
		System.out.println(id+"여기");
		ModelAndView mv = new ModelAndView();
		
		if (ObjectUtils.isEmpty(id)) {
			mv.addObject("msg", "로그인이 필요한 서비스입니다.");
			mv.setViewName("index/index");
		} else {
			session.setAttribute("id", id);
			mv.setViewName("ticketing/INIpayStdSample/ticketing");
		}
		return mv;
	}
	
	
	@RequestMapping(value="/testtt", method=RequestMethod.POST)
	public List<Map<String,Object>> payment(@RequestParam Map<String, Object> param) throws Exception{
	
		int id = ts.payment(param);
		
		List<Map<String,Object>> ticketList = ts.ticketList(id);
		
		
		return ticketList;
		
	}
	
	
	
	@RequestMapping(value="/close", method=RequestMethod.GET)
	public String close() {
		return "ticketing/INIpayStdSample/close";
	}
}
