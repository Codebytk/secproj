package com.mysite.secproj.entity;


import com.mysite.secproj.role.MemberRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	
	@Column(unique=true)
	private String username;
	
	private String password;
	
	private String email;
	
	private String name;
	
	private Integer phonenum;
	
	@Enumerated(EnumType.STRING)
	private MemberRole role;
	
	
	
}
