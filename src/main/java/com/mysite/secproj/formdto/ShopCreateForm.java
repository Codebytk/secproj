package com.mysite.secproj.formdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopCreateForm {


	@NotBlank(message="상품명은 필수 입력입니다.")
	public String itemname;
	
	@NotNull(message="가격은 필수 입력입니다.")
	public Integer price;

	@NotEmpty(message="내용은 필수 입력입니다.")
	public String subject;

	@NotEmpty(message="상세설명은 필수 입력입니다.")
	public String itemdetail;
	
}
