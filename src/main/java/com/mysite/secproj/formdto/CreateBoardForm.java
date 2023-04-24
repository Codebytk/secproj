package com.mysite.secproj.formdto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBoardForm {

	@NotBlank(message="제목은 필수 입력란입니다.")
	private String subject;

	@NotBlank(message="제목은 필수 입력란입니다.")
	private String content;
}
