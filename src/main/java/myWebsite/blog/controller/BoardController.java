package myWebsite.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import myWebsite.blog.dto.BlogDto;
import myWebsite.blog.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/board/showBlogList.do")
	public ModelAndView showBlogList() throws Exception{
		// blogList.html를 불러올 주소 입력
		ModelAndView mv = new ModelAndView("board/blogList");
		
		List<BlogDto> blogList = boardService.getBlogList();
		// model의 addAttribute와 같은 역할
		mv.addObject("blogList", blogList);
		
		return mv;
	}
}
