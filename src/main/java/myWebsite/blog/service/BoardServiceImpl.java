package myWebsite.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import myWebsite.blog.dto.BlogDto;
import myWebsite.blog.dto.BlogFileDto;
import myWebsite.blog.mapper.BoardMapper;
import myWebsite.common.FileUtils;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private FileUtils fileUtils;
	
	@Override
	public List<BlogDto> getBlogList() throws Exception {
		return boardMapper.getBlogList();
	}

	@Override
	public void insertBlog(BlogDto blog, MultipartHttpServletRequest multipartReq) throws Exception {
		boardMapper.insertBlog(blog);
		
		List<BlogFileDto> list = fileUtils.parseFileInfo(blog.getBlogId(), multipartReq);
		if(CollectionUtils.isEmpty(list) == false){
			boardMapper.insertBlogFileList(list);
		}
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
