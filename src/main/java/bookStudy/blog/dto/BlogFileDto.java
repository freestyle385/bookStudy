package bookStudy.blog.dto;

import lombok.Data;

@Data
public class BlogFileDto {
	private int fileId;
	private int blogId;
	private String originalFileName;
	private String storedFilePath;
	private long fileSize;
}
