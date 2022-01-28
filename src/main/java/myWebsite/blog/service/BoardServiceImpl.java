package myWebsite.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myWebsite.blog.dto.BlogDto;
import myWebsite.blog.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<BlogDto> getBlogList() throws Exception {
		return boardMapper.getBlogList();
	}

	@Override
	public void insertBlog(BlogDto blogDto) throws Exception {
		boardMapper.insertBlog(blogDto);
	}

}
