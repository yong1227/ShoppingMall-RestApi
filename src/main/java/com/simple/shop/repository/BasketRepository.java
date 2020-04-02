package com.simple.shop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simple.shop.domain.Basket;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Long>{

	List<Basket> findByUserIdOrderByCreatedAt(Long userId);

	Basket findByUserIdAndProductId(Long userId, Long productId);

	void deleteByUserIdAndProductId(Long userId, Long productId);

	void deleteByUserIdAndId(Long userId, Long basketId);

	//삭제할 때  id로 지워라

}
