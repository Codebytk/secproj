package com.mysite.secproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.secproj.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {

}
