package myWebsite.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import myWebsite.blog.dto.BlogDto;
import myWebsite.blog.dto.BlogFileDto;
import myWebsite.blog.mapper.BoardMapper;
import myWebsite.common.FileUtils;

@Service
@Transactional
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
		int i = 10 / 0;
		BlogDto blog = boardMapper.getBlogDetail(blogId);
		List<BlogFileDto> fileList = boardMapper.getBlogFileList(blogId);
		blog.setFileList(fileList);
		
		return blog;
	}

	@Override
	public void updateBlog(BlogDto blog) throws Exception {
		boardMapper.updateBlog(blog);
	}

	@Override
	public void deleteBlog(int blogId) throws Exception {
		boardMapper.deleteBlog(blogId);
	}

	@Override
	public BlogFileDto getBlogFileInfo(int fileId, int blogId) throws Exception {
		return boardMapper.getBlogFileInfo(fileId, blogId);
	}

}
