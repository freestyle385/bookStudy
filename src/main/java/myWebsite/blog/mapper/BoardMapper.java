package myWebsite.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import myWebsite.blog.dto.BlogDto;
import myWebsite.blog.dto.BlogFileDto;

@Mapper
public interface BoardMapper {
	
	List<BlogDto> getBlogList() throws Exception;

	void insertBlog(BlogDto blog) throws Exception;
	
	void updateHitCnt(int blogId) throws Exception;

	BlogDto getBlogDetail(int blogId) throws Exception;

	void updateBlog(BlogDto blog) throws Exception;

	void deleteBlog(int blogId) throws Exception;

	void insertBlogFileList(List<BlogFileDto> list) throws Exception;
	
}
