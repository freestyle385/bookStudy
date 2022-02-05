package myWebsite.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import myWebsite.blog.dto.BlogDto;
import myWebsite.blog.service.BoardService;

@RestController
public class RestApiBoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping(value="/api/posts", method=RequestMethod.GET)
	public List<BlogDto> showBlogList() throws Exception {

		return boardService.getBlogList();
	}

	@RequestMapping(value="/api/post", method=RequestMethod.POST)
	public void insertBlog(@RequestBody BlogDto blog) throws Exception {
		// 파일 업로드를 위한 multipartReq
		boardService.insertBlog(blog, null);
	}

	@RequestMapping(value="/api/post/{blogId}", method=RequestMethod.GET)
	public BlogDto showBlogDetail(@PathVariable("blogId") int blogId) throws Exception {

		return boardService.getBlogDetail(blogId);
	}

	@RequestMapping(value="/api/post/{blogId}", method=RequestMethod.PUT)
	public String updateBlog(@RequestBody BlogDto blog) throws Exception {
		boardService.updateBlog(blog);

		return "redirect:/posts";
	}

	@RequestMapping(value="/api/post/{blogId}", method=RequestMethod.DELETE)
	public String deleteBlog(@PathVariable("blogId") int blogId) throws Exception {
		boardService.deleteBlog(blogId);

		return "redirect:/posts";
	}

}
