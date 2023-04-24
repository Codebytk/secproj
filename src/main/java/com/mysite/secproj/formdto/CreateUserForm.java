package com.mysite.secproj.formdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserForm {

	@NotBlank(message="사용자 아이디는 필수 입력입니다.")
	public String username;
	
	@NotBlank(message="사용자 비밀번호는 필수 입력입니다.")
	public String password1;

	@NotBlank(message="비밀번호 확인은 필수 입력입니다.")
	public String password2;

	@NotBlank(message="사용자 이름은 필수 입력입니다.")
	public String Mname;
	
	@Email
	public String email;
	
	public Integer phonenum;
	
	public String addr;
	
}
