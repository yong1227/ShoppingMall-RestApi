package com.simple.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.simple.shop.domain.Basket;
import com.simple.shop.domain.Product;
import com.simple.shop.domain.ProductDetail;
import com.simple.shop.domain.Token;
import com.simple.shop.repository.BasketRepository;
import com.simple.shop.repository.ProductRepository;
import com.simple.shop.repository.ProductDetailRepository;
import com.simple.shop.repository.TokenRepository;
import com.simple.shop.repository.UserRepository;

@Service
public class ProductService {
	
	Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired ProductRepository productDAO;
	@Autowired UserRepository userDAO;
	@Autowired BasketRepository basketDAO;
	@Autowired ProductDetailRepository productDetailDAO;
	@Autowired TokenRepository tokenDAO;

	public Page<Product> findProducts(Long morePage) {
		org.springframework.data.domain.Pageable paging = PageRequest.of(morePage.intValue(), 6, Sort.Direction.ASC, "createdAt");
		return productDAO.findAll(paging);
	}

	public Page<Product> findProductsAndUser(String token, Long morePage) {
		org.springframework.data.domain.Pageable paging = PageRequest.of(morePage.intValue(), 6, Sort.Direction.ASC, "createdAt");
		Token tokenVO = tokenDAO.findByToken(token);
		Long userId = tokenVO.getUser().getId();
		
		Page<Product> products = productDAO.findAll(paging);
		
		List<Product> productsList = new ArrayList<Product>();
		productsList = products.getContent();
		
		
		for (Product product : productsList) {
			Long productId = product.getId();
			
			Basket basket = basketDAO.findByUserIdAndProductId(userId, productId);
			
			if(basket == null) {
				product.setIsBasket(false);
			}else {
				product.setIsBasket(true);
			}
		}  
		
		return products;
		
	}

	public Product findProduct(Long productId) {
		return productDAO.findProductById(productId);
	}

	public List<ProductDetail> findProductDetailById(Long productId) {
		return  productDetailDAO.findByProductIdOrderByImageOrder(productId);
	}

	// 비로그인 상품 상세1
	public Product findProductById(Long productId) {
		
		return productDAO.findProductById(productId);
	}

	// 로그인 상품 상세1
	public Product findProductById(Long productId, String token) {
		Token tokenVO = tokenDAO.findByToken(token);
		Long userId = tokenVO.getUser().getId();
		
		Product product = productDAO.findProductById(productId);
		
		Basket basket = basketDAO.findByUserIdAndProductId(userId, productId);
		
		if(basket == null) {
			product.setIsBasket(false);
		}else {
			product.setIsBasket(true);
		}
		
		return product;
	}
}
