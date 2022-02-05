package bookStudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

@SpringBootApplication(exclude= {MultipartAutoConfiguration.class})
//multipartResolver 첨부파일 관련 자동 구성 제외
public class bookStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(bookStudyApplication.class, args);
	}

}
