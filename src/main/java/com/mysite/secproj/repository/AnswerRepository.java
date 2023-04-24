package com.mysite.secproj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.secproj.entity.Answer;
import com.mysite.secproj.entity.Board;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
	
	List<Answer> findByBoardOrderByCreateDate(Board board);

}
