package myWebsite.blog.service;

import java.util.List;

import myWebsite.blog.dto.BlogDto;

public interface BoardService {

	List<BlogDto> getBlogList() throws Exception;

	void insertBlog(BlogDto blogDto) throws Exception;

	BlogDto getBlogDetail(int blogId) throws Exception;

}
