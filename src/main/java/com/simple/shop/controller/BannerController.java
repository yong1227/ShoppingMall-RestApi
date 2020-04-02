package com.simple.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple.shop.domain.Banner;
import com.simple.shop.domain.ResultVO;
import com.simple.shop.service.BannerService;

@RestController
public class BannerController {

	@Autowired
	BannerService bannerService;
	
	
	@GetMapping("/banner")
	public ResultVO findBanner() {
		Page<Banner> banner = bannerService.findBanner();
		return new ResultVO(HttpStatus.OK.value(), "Success", banner);
		
	}
}
