package com.simple.shop.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.shop.domain.Basket;
import com.simple.shop.domain.Product;
import com.simple.shop.domain.Token;
import com.simple.shop.domain.User;
import com.simple.shop.repository.BasketRepository;
import com.simple.shop.repository.ProductRepository;
import com.simple.shop.repository.TokenRepository;
import com.simple.shop.repository.UserRepository;

@Service
public class BasketService {
	
	Logger logger = LoggerFactory.getLogger(BasketService.class);

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
	
	public List<Basket> insertBasket(String token, Long prductId) {
		Token tokenVO = tokenDAO.findByToken(token);
		Long userId = tokenVO.getUser().getId();
		
		Basket basket = new Basket();
		
		logger.info("prductId : "+ prductId);
		
		Product product = productDAO.findProductById(prductId);
		
		User user = userDAO.findUserById(userId);
		basket.setProduct(product);
		basket.setUser(user);
		
		basket.setCreatedAt(new Date());
		basketDAO.save(basket);
		
		return basketDAO.findByUserIdOrderByCreatedAt(userId);
	}
	
	public List<Basket> findBasketByUserId(String token) {
		Token tokenVO = tokenDAO.findByToken(token);
		Long userId = tokenVO.getUser().getId();
		
		return  basketDAO.findByUserIdOrderByCreatedAt(userId);
	}

	public void deleteBasketByProductId(String token, Long basketId) {
		Token tokenVO = tokenDAO.findByToken(token);
		Long userId = tokenVO.getUser().getId();
		
		basketDAO.deleteByUserIdAndId(userId, basketId);
	}
}
