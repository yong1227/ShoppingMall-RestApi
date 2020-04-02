package com.simple.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.simple.shop.domain.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>{

	Page<Product> findAll(Pageable paging);
	
	Product findProductById(Long id);
	
}
