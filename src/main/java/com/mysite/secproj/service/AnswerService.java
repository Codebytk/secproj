package com.mysite.secproj.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mysite.secproj.entity.Answer;
import com.mysite.secproj.entity.Board;
import com.mysite.secproj.entity.Member;
import com.mysite.secproj.repository.AnswerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {
	
	private final AnswerRepository answerRepository;
	
	public void createAnswer(String content,Member member,Board board) {
		Answer answer = new Answer();
		
		answer.setContent(content);
		answer.setBoard(board);
		answer.setCreateDate(LocalDateTime.now());
		answer.setMember(member);
		this.answerRepository.save(answer);
	}
	public List<Answer> answer (Board board){
		
		return this.answerRepository.findByBoardOrderByCreateDate(board);
 	}
 	
}
