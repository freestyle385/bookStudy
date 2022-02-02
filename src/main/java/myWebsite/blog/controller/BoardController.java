package myWebsite.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import myWebsite.blog.dto.BlogDto;
import myWebsite.blog.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping("/board/showBlogList.do")
	public ModelAndView showBlogList() throws Exception {
		// blogList.html를 불러올 주소 입력
		ModelAndView mv = new ModelAndView("board/blogList");

		List<BlogDto> blogList = boardService.getBlogList();
		// model의 addAttribute와 같은 역할
		mv.addObject("blogList", blogList);

		return mv;
	}

	@RequestMapping("/board/showBlogWrite.do")
	public String showBlogWrite() throws Exception {

		return "/board/blogWrite";
	}

	@RequestMapping("/board/insertBlog.do")
	public String insertBlog(BlogDto blog, MultipartHttpServletRequest multipartReq) throws Exception {
		boardService.insertBlog(blog, multipartReq);

		return "redirect:/board/showBlogList.do";
	}

	@RequestMapping("/board/showBlogDetail.do")
	public ModelAndView showBlogDetail(int blogId) throws Exception {
		ModelAndView mv = new ModelAndView("board/blogDetail");

		BlogDto blog = boardService.getBlogDetail(blogId);
		mv.addObject("blog", blog);

		return mv;
	}
	
	@RequestMapping("/board/showBlogUpdate.do")
	public ModelAndView showBlogUpdate(int blogId) throws Exception {
		ModelAndView mv = new ModelAndView("board/blogUpdate");

		BlogDto blog = boardService.getBlogDetail(blogId);
		mv.addObject("blog", blog);
		
		return mv;
	}
	
	@RequestMapping("/board/updateBlog.do")
	public String updateBlog(BlogDto blog) throws Exception {
		boardService.updateBlog(blog);

		return "redirect:/board/showBlogList.do";
	}
	
	@RequestMapping("/board/deleteBlog.do")
	public String deleteBlog(int blogId) throws Exception {
		boardService.deleteBlog(blogId);

		return "redirect:/board/showBlogList.do";
	}
}
