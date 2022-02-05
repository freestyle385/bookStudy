package bookStudy.common;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import bookStudy.blog.dto.BlogFileDto;

@Component
public class FileUtils {

	public List<BlogFileDto> parseFileInfo(int blogId, MultipartHttpServletRequest multipartReq)
			throws Exception {
		
		if (ObjectUtils.isEmpty(multipartReq)) {
			return null;
		}

		List<BlogFileDto> fileList = new ArrayList<>();
		
		// 파일이 업로드될 폴더 생성
		// image 폴더 아래, 날짜를 이름으로 폴더 생성
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
		String path = "images/" + current.format(format);
		File file = new File(path);
		if (file.exists() == false) {
			file.mkdirs();
		}

		Iterator<String> iterator = multipartReq.getFileNames();

		String newFileName, originalFileExtension, contentType;

		while (iterator.hasNext()) {
			List<MultipartFile> list = multipartReq.getFiles(iterator.next());
			for (MultipartFile multipartFile : list) {
				// 파일 형식을 확인하고 이미지의 확장자를 지정
				if (multipartFile.isEmpty() == false) {
					contentType = multipartFile.getContentType();
					if (ObjectUtils.isEmpty(contentType)) {
						break;
					} else {
						if (contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpg";
						} else if (contentType.contains("image/png")) {
							originalFileExtension = ".png";
						} else if (contentType.contains("image/gif")) {
							originalFileExtension = ".gif";
						} else {
							break;
						}
					}
					
					// 서버에 저장될 파일 이름 생성(중복이 없도록 나노 초로 적용)
					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
					
					// DB에 저장할 파일 정보를 BlogFileDto에 저장
					BlogFileDto blogFile = new BlogFileDto();
					blogFile.setBlogId(blogId);
					blogFile.setFileSize(multipartFile.getSize());
					blogFile.setOriginalFileName(multipartFile.getOriginalFilename());
					blogFile.setStoredFilePath(path + "/" + newFileName);
					fileList.add(blogFile);
					
					// 업로드된 파일을 newFileName으로 이름을 바꾸어 지정된 경로에 저장
					file = new File(path + "/" + newFileName);
					multipartFile.transferTo(file);
				}
			}
		}
		return fileList;
	}
}
