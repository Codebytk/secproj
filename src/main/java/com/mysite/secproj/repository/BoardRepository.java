package com.mysite.secproj.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.secproj.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	Page<Board> findByAuthorRole(String string, Pageable pageable);
	
	List<Board> findByAuthorRoleOrderByCreateDateDesc(String string);
	
}
