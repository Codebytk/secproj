package com.mysite.secproj.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Answer {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idx;
	
	@NotBlank(message="내용을 입력해 주세요")
	private String content;
	
	private LocalDateTime createDate;
	
	@ManyToOne
	private Board board;
	
	@ManyToOne
	private Member member;
}
