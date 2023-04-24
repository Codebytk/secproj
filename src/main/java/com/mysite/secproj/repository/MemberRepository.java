package com.mysite.secproj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.secproj.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
		Optional<Member> findByUsername(String username);
}
