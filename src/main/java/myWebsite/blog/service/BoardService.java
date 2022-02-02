package myWebsite.blog.service;

import java.util.List;

import myWebsite.blog.dto.BlogDto;

public interface BoardService {

	List<BlogDto> getBlogList() throws Exception;

	void insertBlog(BlogDto blog) throws Exception;

	BlogDto getBlogDetail(int blogId) throws Exception;

	void updateBlog(BlogDto blog) throws Exception;

	void deleteBlog(int blogId) throws Exception;

}
