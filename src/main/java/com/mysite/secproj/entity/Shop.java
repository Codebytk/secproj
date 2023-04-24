package com.mysite.secproj.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Shop {
	
	@Id
	@Column(name="item id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idx;
	
	private String itemname;
	
	private Integer price;
	
	private String subject;
	
	private String itemdetail;
	
	private String url;
	
	
	
}
