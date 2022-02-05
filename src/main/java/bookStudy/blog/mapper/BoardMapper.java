package bookStudy.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import bookStudy.blog.dto.BlogDto;
import bookStudy.blog.dto.BlogFileDto;

@Mapper
public interface BoardMapper {
	
	List<BlogDto> getBlogList() throws Exception;

	void insertBlog(BlogDto blog) throws Exception;
	
	void updateHitCnt(int blogId) throws Exception;

	BlogDto getBlogDetail(int blogId) throws Exception;

	void updateBlog(BlogDto blog) throws Exception;

	void deleteBlog(int blogId) throws Exception;

	void insertBlogFileList(List<BlogFileDto> list) throws Exception;

	List<BlogFileDto> getBlogFileList(int blogId) throws Exception;

	BlogFileDto getBlogFileInfo(@Param("fileId") int fileId, @Param("blogId") int blogId) throws Exception;
	
}
