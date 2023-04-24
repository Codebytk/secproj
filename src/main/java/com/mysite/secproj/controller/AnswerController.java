package com.mysite.secproj.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite.secproj.entity.Board;
import com.mysite.secproj.entity.Member;
import com.mysite.secproj.formdto.AnswerForm;
import com.mysite.secproj.service.AnswerService;
import com.mysite.secproj.service.BoardService;
import com.mysite.secproj.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
	
	private final BoardService boardService;
	
	private final AnswerService answerService;

	private final MemberService memberService;
	
	@PostMapping("/create/{id}")
	public String createAnswer(Model model,@PathVariable Long id,@Valid AnswerForm answerForm,Principal principal,BindingResult bindingResult) {
		
		Member member = this.memberService.getMember(principal.getName());
		Board board = this.boardService.board(id);
		if(bindingResult.hasErrors()) {
			model.addAttribute("board", board);

			return "board_detail";
		}

		this.answerService.createAnswer(answerForm.getContent(), member, board);
		
		return String.format("redirect:/board/detail/%s", id);
	}

}
