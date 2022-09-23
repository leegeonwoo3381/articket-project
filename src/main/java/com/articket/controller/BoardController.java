package com.articket.controller;


import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.articket.VO.BoardVO;
import com.articket.VO.CommentVO;
import com.articket.VO.CriteriaVO;
import com.articket.VO.PageMakerVO;
import com.articket.service.BoardService;
import com.articket.service.CommentService;

@Controller
public class BoardController {
	@Autowired
	BoardService boardservice;
	
	@Autowired
	CommentService commentservice;

	//게시글 목록
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public ModelAndView board(CriteriaVO cri, BoardVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		ModelAndView mv = new ModelAndView();
		PageMakerVO pageMaker = new PageMakerVO();
		
		response.setContentType("text/html; charset=utf-8");
	    PrintWriter w = response.getWriter();
		
		HttpSession session = request.getSession();
		String value = (String)session.getAttribute("login_id");
		
		int count = boardservice.countList();
		
		if(ObjectUtils.isEmpty(value)) {
			w.write("<script>alert('로그인이 필요한 서비스입니다. :)');location.href='/index'</script>");
			w.flush();
			w.close();
		}else {
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(count);
			
//			List<Object> user_id = boardservice.find_loginId(list);
			
//			mv.addObject("user_id", user_id);
			mv.addObject("list",boardservice.list(cri));
			mv.setViewName("board/board");
			mv.addObject("pageMaker", pageMaker);
		}
		
		return mv;
	}

		
	//	board 글쓰기 게시글 등록
	@RequestMapping(value = "/boardWrite", method = RequestMethod.GET)
	public ModelAndView boardWrite(BoardVO vo, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String value = (String)session.getAttribute("login_id");
		
		ModelAndView mv = new ModelAndView();
		
		if(ObjectUtils.isEmpty(value)) {
			mv.setViewName("index/index");
		}else {
			mv.addObject("user_id",value);
			mv.setViewName("board/boardWrite");
		}
		return mv;
	}
	
	@RequestMapping(value = "/boardWrite", method = RequestMethod.POST)
	public ModelAndView createPost(@RequestParam Map<String, Object> map) throws Exception{
		ModelAndView mv = new ModelAndView();
		System.out.println(map);
		String resultType = this.boardservice.create(map);
		if("ERROR".equals(resultType)) {
			mv.setViewName("redirect:/boardWrite");
		}else {
			mv.setViewName("redirect:/board");
		}
		return mv;
	}
	
	//상세페이지
	@RequestMapping(value = "/boardDetail", method=RequestMethod.GET)
	public ModelAndView boardDetail(@RequestParam Map<String, Object> map, HttpServletRequest request) throws Exception{
		String board_no = (String) map.get("boardId");
	
		HttpSession session = request.getSession();
		String value = (String)session.getAttribute("login_id");
	
		
		Map<String, Object> boardDetailMap = this.boardservice.detail(map);
		
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("list", boardDetailMap);
		
		String boardId = map.get("boardId").toString();
		
		String result = boardservice.updateViewCnt(boardId);
		
		mv.addObject("boardId", boardId);
		mv.setViewName("board/boardDetail");
		
		
		// 댓글 조회
		List<CommentVO> commentList = commentservice.readComment(board_no);
		mv.addObject("commentList", commentList);
		mv.addObject("boardNo", board_no);
		 
		
		
		return mv;
	}
	
	
	//게시글 수정
	@RequestMapping(value = "/boardUpdate", method = RequestMethod.GET)  
	public ModelAndView update(@RequestParam Map<String, Object> map) throws Exception{  
	Map<String, Object> detailMap = this.boardservice.detail(map); 

	ModelAndView mv = new ModelAndView();  
		mv.addObject("list", detailMap);  
		mv.setViewName("board/boardUpdate");  
		return mv;  
	}  
	@RequestMapping(value = "/boardUpdate", method=RequestMethod.POST)
	public ModelAndView updatePost(@RequestParam Map<String, Object> map) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		boolean isUpdateSuccess = this.boardservice.update(map);
		if(isUpdateSuccess) {
			String boardId = map.get("boardId").toString();
			mv.setViewName("redirect:/boardDetail?boardId=" + boardId); 
		}else {
			mv = this.update(map);
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deletePost(@RequestParam Map<String, Object> map) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		boolean isDeleteSuccess = this.boardservice.remove(map);
		if(isDeleteSuccess) {
			mv.setViewName("redirect:/board");
		}else {
			String boardId = map.get("boardId").toString();
			mv.setViewName("redirect:/board/boardDetail?boardId=" + boardId);	
		}
		return mv;
	}
	


}
