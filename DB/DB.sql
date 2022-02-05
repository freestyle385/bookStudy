DROP DATABASE IF EXISTS myWebsite;
CREATE DATABASE myWebsite;
USE myWebsite;

#블로그 게시물 테이블
CREATE TABLE blog(
    blog_id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '글 번호',
	title VARCHAR(300) NOT NULL COMMENT '제목',
	`body` TEXT NOT NULL COMMENT '내용',
	writer_id INT(10) UNSIGNED NOT NULL COMMENT '작성자',
	hit_cnt SMALLINT(10) NOT NULL DEFAULT '0' COMMENT '조회수',
	reg_date DATETIME NOT NULL DEFAULT NOW() COMMENT '작성일자',
	upd_date DATETIME NOT NULL DEFAULT NOW() COMMENT '수정일자'
);

# 블로그 파일 저장 테이블
CREATE TABLE blog_file(
    file_id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '파일 번호',
    blog_id INT(10) UNSIGNED NOT NULL COMMENT '게시글 번호',
    original_file_name VARCHAR(500) NOT NULL COMMENT '원본 파일 이름',
    stored_file_path VARCHAR(500) NOT NULL COMMENT '파일 저장 경로',
    file_size INT(15) UNSIGNED NOT NULL COMMENT '파일 크기',
    writer_id INT(10) UNSIGNED NOT NULL COMMENT '작성자',
    reg_date DATETIME NOT NULL DEFAULT NOW() COMMENT '작성일자',
    upd_date DATETIME NOT NULL DEFAULT NOW() COMMENT '수정일자'
);