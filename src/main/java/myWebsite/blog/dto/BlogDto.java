package myWebsite.blog.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BlogDto {
	private int blogId;
	private String title;
	private String body;
	private String writerId;
	private int hitCnt;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
}
