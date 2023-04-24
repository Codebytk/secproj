package com.mysite.secproj.service;

import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BoardImgService {

	@Value("${itemImgLocation}")
	private String questionImgLocation;
	
	private final FileService fileService;
	
	public String returnBoardImg(MultipartFile img) throws Exception{
		
		String oriName = img.getOriginalFilename();
		String name ="";
		String url = "";
		
		if(!StringUtils.isEmpty(oriName)) {
			name = fileService.uploadFile(questionImgLocation, oriName, img.getBytes());
			return url = "/images/item/"+name;
		}
		
		return null;
		
	}
	
	
}
