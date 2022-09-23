package com.articket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.articket.VO.UserVO;

@Controller
public class DetailController {
	
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public ModelAndView detail(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVO uvo = (UserVO) session.getAttribute("nickname");
		int id = uvo.getId();
		System.out.println(id+"여기");
		ModelAndView mv = new ModelAndView();
			mv.setViewName("detail/theaterdetail");
		return mv;
		
//		수정사항: 모델엔뷰->  String 데이터타입 바꾸고
		// 모델에 메세지 담고, 리턴에 리다이렉트로 url 변경되게  
	}

}
