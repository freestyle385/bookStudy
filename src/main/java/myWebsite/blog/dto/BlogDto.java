package myWebsite.blog.dto;

import java.util.List;

import lombok.Data;

@Data
public class BlogDto {
	private int blogId;
	private String title;
	private String body;
	private String writerId;
	private int hitCnt;
	private String regDate;
	private String updDate;
	private List<BlogFileDto> fileList;
}
