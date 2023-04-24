package com.mysite.secproj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mysite.secproj.entity.Shop;
import com.mysite.secproj.entity.ShopImg;
import com.mysite.secproj.repository.ShopImgRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopImgService {

	private final BoardImgService boardImgService;
	
	private final ShopImgRepository shopImgRepository;
	
	
	
	public void save(ShopImg shopImg,MultipartFile file) throws Exception{
		
		shopImg.setUrl(this.boardImgService.returnBoardImg(file));
		
		this.shopImgRepository.save(shopImg);
	}
	public List<ShopImg> detailImg(Optional<Shop> shop) {
		
		return this.shopImgRepository.findByShopOrderByIdxAsc(shop);
	}
	
}
