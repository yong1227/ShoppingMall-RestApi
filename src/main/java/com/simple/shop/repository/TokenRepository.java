package com.simple.shop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simple.shop.domain.Token;

@Repository
public interface TokenRepository extends CrudRepository<Token, Long>{
	
	Token findByToken(String token);
}
