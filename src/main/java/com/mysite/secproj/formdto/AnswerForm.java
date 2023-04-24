package com.mysite.secproj.formdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerForm {

	@NotBlank
	@Size(max=200)
	private String content;
}
