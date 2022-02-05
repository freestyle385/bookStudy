package myWebsite.blog.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import myWebsite.blog.dto.BlogDto;
import myWebsite.blog.dto.BlogFileDto;
import myWebsite.blog.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/posts", method = RequestMethod.GET)
	public ModelAndView showBlogList() throws Exception {
		// blogList.html를 불러올 주소 입력
		ModelAndView mv = new ModelAndView("board/blogList");

		List<BlogDto> blogList = boardService.getBlogList();
		// model의 addAttribute와 같은 역할
		mv.addObject("blogList", blogList);

		return mv;
	}
	
	@RequestMapping(value = "/posts/new-post", method = RequestMethod.GET)
	public String showBlogWrite() throws Exception {

		return "/board/blogWrite";
	}

	@RequestMapping(value = "/posts", method = RequestMethod.POST)
	public String insertBlog(BlogDto blog, MultipartHttpServletRequest multipartReq) throws Exception {
		// 파일 업로드를 위한 multipartReq
		boardService.insertBlog(blog, multipartReq);
		
		return "redirect:/posts";
	}

	@RequestMapping(value = "/posts/{blogId}", method = RequestMethod.GET)
	public ModelAndView showBlogDetail(@PathVariable("blogId") int blogId) throws Exception {
		ModelAndView mv = new ModelAndView("board/blogDetail");

		BlogDto blog = boardService.getBlogDetail(blogId);
		mv.addObject("blog", blog);

		return mv;
	}

	@RequestMapping(value = "/posts/{blogId}/edit", method = RequestMethod.GET)
	public ModelAndView showBlogUpdate(@PathVariable("blogId") int blogId) throws Exception {
		ModelAndView mv = new ModelAndView("board/blogUpdate");

		BlogDto blog = boardService.getBlogDetail(blogId);
		mv.addObject("blog", blog);

		return mv;
	}
	
	@RequestMapping(value = "/posts/{blogId}", method = RequestMethod.PUT)
	public String updateBlog(BlogDto blog) throws Exception {
		boardService.updateBlog(blog);

		return "redirect:/posts";
	}

	@RequestMapping(value = "/posts/{blogId}", method = RequestMethod.DELETE)
	public String deleteBlog(@PathVariable("blogId") int blogId) throws Exception {
		boardService.deleteBlog(blogId);

		return "redirect:/posts";
	}
	
	@RequestMapping(value = "/posts/{blogId}/file/{fileId}", method = RequestMethod.GET)
	public void downloadBlogFile(@PathVariable("blogId") int blogId, @PathVariable("fileId") int fileId, HttpServletResponse res)
			throws Exception {
		BlogFileDto blogFile = boardService.getBlogFileInfo(fileId, blogId);

		if (ObjectUtils.isEmpty(blogFile) == false) {
			String fileName = blogFile.getOriginalFileName();
			
			// 조회한 file의 저장 위치로 실제 저장되어 있는 파일을 읽어온 후 byte[]로 변환 
			byte[] files = FileUtils.readFileToByteArray(new File(blogFile.getStoredFilePath()));
			
			// response의 헤더에 콘텐츠 타입, 크기, 형태 설정
			res.setContentType("application/octet-stream");
			res.setContentLength(files.length);
			res.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
			res.setHeader("Content-Transfer-Encoding", "binary");
			
			// byte[] 형태의 파일 정보 데이터를 response에 작성
			res.getOutputStream().write(files);
			
			// response 버퍼 닫기
			res.getOutputStream().flush();
			res.getOutputStream().close();
		}
	}
}
