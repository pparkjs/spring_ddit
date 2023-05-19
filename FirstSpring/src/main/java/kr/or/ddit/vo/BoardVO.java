package kr.or.ddit.vo;

import lombok.Data;

// @Data 어노테이션 만으로 getter/setter를 완성시켜준다 -> lombok library 이용! 
@Data
public class BoardVO {
	private int boNo;			// 일반 게시판 변호
	private String boTitle;		// 일반 게시판 제목
	private String boWriter;	// 일반 게시판 작성자
	private String boContent;	// 일반 게시판 내용
	private String boDate;		// 일반 게시판 작성일
	private int boHit;			// 일반 게시판 조회수
}