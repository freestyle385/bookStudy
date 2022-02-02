package myWebsite.blog.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import myWebsite.blog.dto.BlogDto;
import myWebsite.blog.dto.BlogFileDto;

public interface BoardService {

	List<BlogDto> getBlogList() throws Exception;

	void insertBlog(BlogDto blog, MultipartHttpServletRequest multipartReq) throws Exception;

	BlogDto getBlogDetail(int blogId) throws Exception;

	void updateBlog(BlogDto blog) throws Exception;

	void deleteBlog(int blogId) throws Exception;
	
	BlogFileDto getBlogFileInfo(int fileId, int blogId) throws Exception;

}
