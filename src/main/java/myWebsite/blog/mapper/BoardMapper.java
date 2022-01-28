package myWebsite.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import myWebsite.blog.dto.BlogDto;

@Mapper
public interface BoardMapper {
	
	List<BlogDto> getBlogList() throws Exception;
	
}
