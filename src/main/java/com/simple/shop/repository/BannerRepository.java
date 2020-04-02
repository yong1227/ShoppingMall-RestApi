package com.simple.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.simple.shop.domain.Banner;

@Repository
public interface BannerRepository extends PagingAndSortingRepository<Banner, Long>{

	Page<Banner> findAll(Pageable paging);
}
