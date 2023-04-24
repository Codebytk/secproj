package com.mysite.secproj.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mysite.secproj.entity.Board;
import com.mysite.secproj.entity.Member;
import com.mysite.secproj.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	private final BoardImgService boardImgService;
	public Page<Board> listBoard(Integer page){
		List<Sort.Order>sort = new ArrayList();
		sort.add(Sort.Order.desc("createDate"));
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sort));
		
		return this.boardRepository.findByAuthorRole("USER",pageable);
	}
	
	public List<Board> adminList(){
		
		return this.boardRepository.findByAuthorRoleOrderByCreateDateDesc("ADMIN");
	}
	
	public void create(String subject, String content, String author, Member member, MultipartFile file) throws Exception{
		Board board = new Board();
		board.setSubject(subject);
		board.setContent(content);
		board.setCreateDate(LocalDateTime.now());
		board.setAuthorRole(author);
		board.setMember(member);
		board.setUrl(this.boardImgService.returnBoardImg(file));
		this.boardRepository.save(board);
	}
	public Board board(Long id) {
		Optional<Board> board = this.boardRepository.findById(id);
		return board.get();
	}
	
}
