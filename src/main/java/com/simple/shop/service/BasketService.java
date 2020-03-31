package com.simple.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.simple.shop.domain.Basket;
import com.simple.shop.domain.Product;
import com.simple.shop.domain.Token;
import com.simple.shop.repository.BasketRepository;
import com.simple.shop.repository.ProductRepository;
import com.simple.shop.repository.TokenRepository;
import com.simple.shop.repository.UserRepository;

@Service
public class BasketService {

	@Autowired
	BasketRepository basketDAO;
	
	@Autowired
	UserRepository userDAO;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository productDAO;
	
	@Autowired
	TokenRepository tokenDAO;
	
	public List<Basket> insertBasket(String token, Basket basketVO) {
		Token tokenVO = tokenDAO.findByToken(token);
		Long userId = tokenVO.getUser().getId();
		basketVO.getUser().setId(userId);
		
		basketDAO.save(basketVO);
		
		return basketDAO.findByUserIdOrderByCreatedAt(userId);
	}
	
	public List<Basket> findBasketByUserId(String token) {
		Token tokenVO = tokenDAO.findByToken(token);
		Long userId = tokenVO.getUser().getId();
		
		return  basketDAO.findByUserIdOrderByCreatedAt(userId);
	}

	public void deleteBasketByProductId(String token, Long productId) {
		Token tokenVO = tokenDAO.findByToken(token);
		Long userId = tokenVO.getUser().getId();
		
		basketDAO.deleteByUserIdAndProductId(userId, productId);
	}
}
