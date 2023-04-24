package com.mysite.secproj.controller;

import java.security.Principal;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.secproj.entity.Member;
import com.mysite.secproj.formdto.CreateUserForm;
import com.mysite.secproj.role.MemberRole;
import com.mysite.secproj.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	
	private final MemberService memberService;

	@GetMapping("/create")
	public String memberCreate(CreateUserForm createUserForm) {
		
		return "member_create";
	} 
	@PostMapping("/create")
	public String createMember(@Valid CreateUserForm createUserForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "member_create";
			
		}
		if(!createUserForm.getPassword1().equals(createUserForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordInCorrect","두개의 패스워드가 일치하지 않습니다");
			return "member_create";
		}
		String a = createUserForm.getUsername();
		if(a.contains(".")) {
			String admin = createUserForm.getUsername().substring(createUserForm.getUsername().lastIndexOf("."));
			if(admin.equals(".admin")) {
				try {
					memberService.createMember(createUserForm.getUsername(), createUserForm.getPassword1(), 
							createUserForm.getMname(),createUserForm.getEmail(), createUserForm.getPhonenum(),MemberRole.ADMIN);
				}catch(DataIntegrityViolationException e) {
					e.printStackTrace();
					bindingResult.reject("singupFail","이미 등록된 사용자 입니다.");
					return "member_create";
				}catch(Exception e) {
					e.printStackTrace();
					bindingResult.reject("singupFailed",e.getMessage());
					return "member_create";
				}
				return "index";
				}else {
					try {
						memberService.createMember(createUserForm.getUsername(), createUserForm.getPassword1(), createUserForm.getMname(),
								createUserForm.getEmail(), createUserForm.getPhonenum(),MemberRole.USER);
					}catch(DataIntegrityViolationException e) {
						e.printStackTrace();
						bindingResult.reject("singupFail","이미 등록된 사용자 입니다.");
						return "member_create";
					}catch(Exception e) {
						e.printStackTrace();
						bindingResult.reject("singupFailed",e.getMessage());
						return "member_create";
					}
		
					return "index";
				}
			}else {
				try {
					memberService.createMember(createUserForm.getUsername(), createUserForm.getPassword1(), createUserForm.getMname(),
							createUserForm.getEmail(), createUserForm.getPhonenum(),MemberRole.USER);
				}catch(DataIntegrityViolationException e) {
					e.printStackTrace();
					bindingResult.reject("singupFail","이미 등록된 사용자 입니다.");
					return "member_create";
				}catch(Exception e) {
					e.printStackTrace();
					bindingResult.reject("singupFailed",e.getMessage());
					return "member_create";
				}
	
				return "index";
			}
	
		
	}

	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
	@GetMapping("/information")
	public String information(Model model,Principal principal) {
		
		
		
		
		return "information";
	}
	@PostMapping("/ajaxtest")
	public String ajax(@RequestParam("username")String username) {
		System.out.println(username);
		try {
			Member member = this.memberService.getMember(username);
			return "이미 사용중인 아이디 입니다.";
		}catch(Exception e) {
			e.printStackTrace();
			return "사용가능한 아이디 입니다.";
		}
	}
	
}
