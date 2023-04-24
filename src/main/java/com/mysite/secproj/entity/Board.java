package com.mysite.secproj.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Board {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idx;
	
	private String subject;
	
	private String content;
	
	private LocalDateTime createDate;
	
	@ManyToOne
	private Member member;
	
	@OneToMany(mappedBy = "board", cascade=CascadeType.REMOVE)
	private List<Answer> answerList;
	
	private String authorRole;
	
	private String url;
	
}
