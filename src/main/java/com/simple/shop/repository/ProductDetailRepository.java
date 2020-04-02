package com.simple.shop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simple.shop.domain.ProductDetail;

@Repository
public interface ProductDetailRepository extends CrudRepository<ProductDetail, Long>{

	List<ProductDetail> findByProductIdOrderByImageOrder (Long productId);

}
