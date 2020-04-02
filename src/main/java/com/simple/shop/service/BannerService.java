package com.simple.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.simple.shop.domain.Banner;
import com.simple.shop.repository.BannerRepository;

@Service
public class BannerService {
	@Autowired
	BannerRepository bannerDAO;
	
	public Page<Banner> findBanner() {
		Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "createdAt");
		return bannerDAO.findAll(paging);
		 
	}
}
