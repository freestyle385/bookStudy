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
	public void insertBlog(BlogDto blog) throws Exception {
		boardMapper.insertBlog(blog);
	}

	@Override
	public BlogDto getBlogDetail(int blogId) throws Exception {
		boardMapper.updateHitCnt(blogId);
		
		return boardMapper.getBlogDetail(blogId);
	}

	@Override
	public void updateBlog(BlogDto blog) throws Exception {
		boardMapper.updateBlog(blog);
	}

	@Override
	public void deleteBlog(int blogId) throws Exception {
		boardMapper.deleteBlog(blogId);
	}

}
