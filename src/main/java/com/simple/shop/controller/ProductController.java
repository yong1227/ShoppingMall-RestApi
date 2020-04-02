package com.simple.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simple.shop.domain.Product;
import com.simple.shop.domain.ResultVO;
import com.simple.shop.repository.UserRepository;
import com.simple.shop.service.BasketService;
import com.simple.shop.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	BasketService basketService;

	@Autowired
	UserRepository userDAO;

	@GetMapping("/products")
	public ResultVO findProducts(@CookieValue(value = "token", required = false) String token, @RequestParam("morePage") Long morePage) {

		if (token == null) {
			Page<Product> products = productService.findProducts(morePage);
			return new ResultVO(HttpStatus.OK.value(), "Success", products);
		} else {
			Page<Product> productsAndUsers = productService.findProductsAndUser(token, morePage);
			return new ResultVO(HttpStatus.OK.value(), "Success", productsAndUsers);
		}
	}
	
	@GetMapping("/product/{productId}")
	public ResultVO findProductById(@CookieValue(value = "token", required = false) String token, @PathVariable("productId") Long productId) {
		if(token == null ) {
			return new ResultVO(HttpStatus.OK.value(), "Success", productService.findProductById(productId));	
		}else {
			return new ResultVO(HttpStatus.OK.value(), "Success", productService.findProductById(productId, token));
		}
	}
	
	@GetMapping("/product/detail/{productId}")
	public ResultVO findProductDetailById(@PathVariable("productId") Long productId) {
		return new ResultVO(HttpStatus.OK.value(), "Success", productService.findProductDetailById(productId));
	}
}
