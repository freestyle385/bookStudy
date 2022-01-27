package myWebsite.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BlogDto {
	private int id;
	private String title;
	private String body;
	private String writerId;
	private int hitCnt;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
}
