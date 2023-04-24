package com.mysite.secproj.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mysite.secproj.entity.Shop;
import com.mysite.secproj.entity.ShopImg;
import com.mysite.secproj.formdto.ShopCreateForm;
import com.mysite.secproj.service.ShopImgService;
import com.mysite.secproj.service.ShopService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ShopController {

	private final ShopService shopService;
	private final ShopImgService shopImgService;
	
	@GetMapping("/shop")
	public String listShop(Model model) {
		
		List<Shop> shop = this.shopService.shop();
		
		model.addAttribute("shop", shop);
		
		return "detail4";
	}
	
	@GetMapping("/shop/create")
	public String create(ShopCreateForm shopCreateFOrm){
		
		return "shop_create";
	}
	@PostMapping("/shop/create")
	public String createForm(@RequestParam List<MultipartFile> file,@Valid ShopCreateForm shopCreateForm, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "shop_create";
		}
		try {
			this.shopService.save(shopCreateForm.getItemname(), shopCreateForm.getPrice(), shopCreateForm.getSubject(), shopCreateForm.getItemdetail(),file);
		}catch(Exception e) {
			e.printStackTrace();
			return "board_create";
		}
		
		return "redirect:/shop";
	}
	@GetMapping(value="/shop/detail/{id}")
	public String detailShop(Model model, @PathVariable("id") Long idx) {
		
		Optional<Shop> getshop = this.shopService.detailshop(idx);
		
		Shop shop = getshop.get();
		
		List<ShopImg> shopImg = this.shopImgService.detailImg(getshop);
		
		model.addAttribute("shop", shop);
		model.addAttribute("detailimg", shopImg);
		return "detail5";
	}
}
