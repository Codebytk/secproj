package com.mysite.secproj.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mysite.secproj.entity.Answer;
import com.mysite.secproj.entity.Board;
import com.mysite.secproj.entity.Member;
import com.mysite.secproj.formdto.AnswerForm;
import com.mysite.secproj.formdto.CreateBoardForm;
import com.mysite.secproj.service.AnswerService;
import com.mysite.secproj.service.BoardService;
import com.mysite.secproj.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	
	private final BoardService boardService;
	private final AnswerService answerService;
	private final MemberService memberService;

	@GetMapping("/list")
	public String list(Model model, @RequestParam(value="page",defaultValue="0") Integer page) {
		
		List<Board> admin = this.boardService.adminList();
		
		model.addAttribute("admin",admin);
		
		Page<Board> board = this.boardService.listBoard(page);
		model.addAttribute("board",board);
		return "board_list";
	}
	@PreAuthorize("isAuthenticated")
	@GetMapping("/create")
	public String creaet(CreateBoardForm createBoardForm) {
		
		return "board_create";
	}
	@PreAuthorize("isAuthenticated")
	@PostMapping("/create")
	public String create(@RequestParam MultipartFile file,@Valid CreateBoardForm createBoardForm,
			BindingResult bindingResult, Principal principal) {
		if(bindingResult.hasErrors()) {
			return "board_create";
		}
		Member member = this.memberService.getMember(principal.getName());
		try {
		this.boardService.create(createBoardForm.getSubject(),
				createBoardForm.getContent(),"USER", member,file);
		}catch(Exception e) {
			e.printStackTrace();
			return "board_create";
		}
		return String.format("redirect:/board/list");
	}

	@PreAuthorize("isAuthenticated")
	@GetMapping("/admin/create")
	public String adminCreate(CreateBoardForm createBoardForm) {
		return "admin_board";
	}
	@PostMapping("/admin/create")
	public String adminCreate(@RequestParam MultipartFile file,@Valid CreateBoardForm createBoardForm, BindingResult bindingResult, Principal principal) {
		if(bindingResult.hasErrors()) {
			return "admin_board";
		}
		Member member = this.memberService.getMember(principal.getName());
		try {
			this.boardService.create(createBoardForm.getSubject(), createBoardForm.getContent(),"ADMIN", member,file);
			}catch(Exception e) {
				e.printStackTrace();
				return "admin_board";
			}
		return String.format("redirect:/board/list");
	}

	@PreAuthorize("isAuthenticated")
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Long idx,Model model, AnswerForm answerForm) {
		
		Board board = this.boardService.board(idx);
		List<Answer> answer = this.answerService.answer(board);
		model.addAttribute("board", board);
		model.addAttribute("answer", answer);
		
		return "board_detail";
	}
	
}
