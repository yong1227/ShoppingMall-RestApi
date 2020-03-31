package com.simple.shop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simple.shop.domain.ProductDetail;
import com.simple.shop.domain.Product;

@Repository
public interface ProductDetailRepository extends CrudRepository<ProductDetail, Long>{

	List<ProductDetail> findByProductIdOrderByImageOrder (Long productId);

}
