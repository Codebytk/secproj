package com.mysite.secproj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mysite.secproj.entity.Shop;
import com.mysite.secproj.entity.ShopImg;
import com.mysite.secproj.repository.ShopRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopService {
	
	private final ShopRepository shopRepository;
	private final ShopImgService shopImgService;
	private final BoardImgService boardImgService;
	
	public void save(String itemname, Integer price, String subject, 
			String itemdetail,List<MultipartFile> file) throws Exception {
		Shop shop = new Shop();
		shop.setItemname(itemname);
		shop.setPrice(price);
		shop.setSubject(subject);
		shop.setItemdetail(itemdetail);
		this.shopRepository.save(shop);
		for(int i=0; i<file.size(); i++) {
			ShopImg shopImg = new ShopImg();
			shopImg.setShop(shop);
			if(i == 0) {
				shop.setUrl(boardImgService.returnBoardImg(file.get(i)));
				shopImg.setRepingYN("Y");
			}else {
				shopImg.setRepingYN("N");
			}
			this.shopImgService.save(shopImg, file.get(i));
		}
		
	}
	public List<Shop> shop(){
		
		return this.shopRepository.findAll();
	}
	
	public Optional<Shop> detailshop(Long idx){
		return this.shopRepository.findById(idx);
	}
}