package com.simple.shop.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simple.shop.domain.ResultVO;
import com.simple.shop.domain.User;
import com.simple.shop.service.UserService;

@RestController
public class UserController {

	@Autowired UserService userService;
	
	@PostMapping("/user")
	public ResultVO insertUser(@RequestBody User userVO) throws ParseException {
		return userService.insertUser(userVO);
	}
	
	@GetMapping("/emailCheck")
	public ResultVO findUserByEmail(@RequestParam("email") String email ) {
		
		int emailCheck = userService.findUserByEmail(email);
		
		if (emailCheck > 0) {
			return new ResultVO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", " 가입된 이메일이 있습니다.");	
		}else {
			return new ResultVO(HttpStatus.OK.value(), "Success", "가입가능한 이메일입니다.");
		}
	}
	
	@PostMapping("/auth")
	public ResultVO login(@RequestBody User userVO) {
		String email = userVO.getEmail();
		String password = userVO.getPassword();
		userVO = userService.findUserByEmailAndPassword(email, password);
		if(userVO == null) {
			return new ResultVO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", " 이메일 또는 비밀번호 정보가 맞지 않습니다.");	
		}
		return new ResultVO(HttpStatus.OK.value(), "Succuess", userService.login(userVO));
	}
}
