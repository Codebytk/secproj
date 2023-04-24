package com.mysite.secproj.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ShopImg {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idx;
	
	private String url;
	
	private String repingYN;
	
	@ManyToOne
	private Shop shop;

}
