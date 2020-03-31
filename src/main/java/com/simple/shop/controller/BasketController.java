package com.simple.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simple.shop.domain.Basket;
import com.simple.shop.domain.Product;
import com.simple.shop.domain.ResultVO;
import com.simple.shop.service.BasketService;

@RestController
public class BasketController {

	@Autowired
	BasketService basketService;
	
	@GetMapping("/basket")
	public ResultVO findBasketByUserId(@CookieValue(value = "token") String token ) {
		
		List<Basket> productAndBaskets = basketService.findBasketByUserId(token);
		
		return new ResultVO(HttpStatus.OK.value(), "Success", productAndBaskets);
	}
	
	@PostMapping("/basket")
	public ResultVO insertBasket(@CookieValue (value = "token") String token, @RequestBody Basket basketVO) {
		
		List<Basket> baskets = basketService.insertBasket(token, basketVO);
		return new ResultVO(HttpStatus.OK.value(), "Success", baskets);
	}
	
	@DeleteMapping("/basket/{productId}")
	public ResultVO deleteBasketByProductId(@CookieValue(value = "token") String token ,  @PathVariable("productId") Long productId ) {
		 basketService.deleteBasketByProductId(token,productId);
		 return new ResultVO(HttpStatus.OK.value(), "Success", "delete Success");
	}
}
