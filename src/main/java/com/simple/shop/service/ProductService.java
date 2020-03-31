package com.simple.shop.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.simple.shop.domain.Basket;
import com.simple.shop.domain.Product;
import com.simple.shop.domain.ProductDetail;
import com.simple.shop.domain.Token;
import com.simple.shop.domain.User;
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
		logger.info("productsList : "+ productsList);
		
		for (Product product : productsList) {
			User user = userDAO.findUserById(userId);
			logger.info("user1 : "+ user);
			product.setUser(user);
			logger.info("productsList2 ="+productsList);
			
			Long productId = product.getId();
			
			List<Basket> baskets = basketDAO.findByUserIdOrderByCreatedAt(userId);
			logger.info("basket : "+ baskets);
			
			if(baskets.size()== 0 || baskets.equals(null)) {
				user.setIsBasket(false);
				product.setUser(user);
			}else {
				for (Basket basket : baskets) {
					 Long productIdByBasket = basket.getProduct().getId();
					 logger.info("productId : "+ productId);
					 logger.info("productIdByBasket : "+productIdByBasket);
					 
					 if(productId == productIdByBasket || productId.equals(productIdByBasket)) {
						 
						 user.setIsBasket(true);
						 product.setUser(user);
						 break;
					 }
					 else {
						 user.setIsBasket(false);
						 product.setUser(user);
						 
					 }
				}
			}
		}
		
		
		
		
		return products;
		
		
		
		
		
//		for (Product product : products) {
//			Long productId = product.getId();
//			
//			List<Basket> baskets = basketDAO.findByUserIdOrderByCreatedAt(userId);
//			logger.info("baskets : " + baskets);
//			if(baskets.size()==0) {
//				for (Basket basket : baskets) {
//					if(basket.getUser().getId() == userId || basket.getUser().getId().equals(userId)) {
//						basket.getUser().setIsBasket(false);
//					}
//				}
//			}else {
//				for (Basket basketVO : baskets) {
//					Long productIdByBasket = basketVO.getProduct().getId();
//					logger.info("productIdByBasket : "+ productIdByBasket);
//					if(productId == productIdByBasket || productId.equals(productIdByBasket)) {
//						for (Basket basket : baskets) {
//							basket.getUser().setIsBasket(true);
//						}
//						break;
//					}else {
//						for (Basket basket : baskets) {
//							basket.getUser().setIsBasket(false);
//						}
//					}
//				}
//			}
//		}
//		
//		return products;
//		
	}

	public Product findProduct(Long productId) {
		return productDAO.findProductById(productId);
	}

//	public Product findProductAndUser(String token, Long productId) {
//		Token tokenVO = tokenDAO.findByToken(token);
//		Long userId = tokenVO.getUser().getId();
//		
//		Product productAndUser = productDAO.findByUserIdAndId(userId, productId);
//		logger.info("productAndUser :" + productAndUser);
//		
//		Basket basketVO = new Basket();
////		basketVO.getProduct().setId(productId);
////		basketVO.getUser().setId(userId);
//		
//		basketVO = basketDAO.findByUserIdAndProductId(userId, productId);
//
//		if(basketVO == null ) {
//			productAndUser.getUser().setIsBasket(false);
//		}else {
//			productAndUser.getUser().setIsBasket(true);
//		}
//		return productAndUser;
//	}

	public List<ProductDetail> findProductDetailById(Long productId) {
		return  productDetailDAO.findByProductIdOrderByImageOrder(productId);
	}
}
