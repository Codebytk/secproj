package com.mysite.secproj.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysite.secproj.entity.Member;
import com.mysite.secproj.repository.MemberRepository;
import com.mysite.secproj.role.MemberRole;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	public void createMember(String username, String password, String mName,String email, Integer phonenum, MemberRole role) {
		Member member = new Member();
		member.setUsername(username);
		member.setPassword(this.passwordEncoder.encode(password));
		member.setName(mName);
		member.setEmail(email);
		member.setPhonenum(phonenum);
		member.setRole(role);
		this.memberRepository.save(member);
	}
	public Member getMember(String username) {
		Optional<Member> member =this.memberRepository.findByUsername(username);
			return member.get();
	}
	
}
