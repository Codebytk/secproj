package com.mysite.secproj.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.secproj.entity.Shop;
import com.mysite.secproj.entity.ShopImg;

public interface ShopImgRepository extends JpaRepository<ShopImg, Long> {
	
	List<ShopImg> findByShopOrderByIdxAsc(Optional<Shop> shop);
}
