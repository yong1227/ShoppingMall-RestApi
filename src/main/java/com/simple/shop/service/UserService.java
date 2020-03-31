package com.simple.shop.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.simple.shop.domain.ResultVO;
import com.simple.shop.domain.Token;
import com.simple.shop.domain.User;
import com.simple.shop.repository.BasketRepository;
import com.simple.shop.repository.ProductRepository;
import com.simple.shop.repository.TokenRepository;
import com.simple.shop.repository.UserRepository;
import com.simple.shop.util.RandomToken;

@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	UserRepository userDAO;
	
	@Autowired
	BasketRepository basketDAO;
	
	@Autowired
	ProductRepository productDAO;
	
	@Autowired
	TokenRepository tokenDAO;
	
	public ResultVO insertUser(User userVO) throws ParseException {

		String password = userVO.getPassword();
		String passwordConfirm = userVO.getPasswordConfirm();
		
		// 비밀번호 다를 경우
		if(!password.equals(passwordConfirm)) {
			return new ResultVO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", "비밀번호가 다릅니다.");
		}
		
		// 나이가 7세 이하인 경우 회원 가입 불가
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		String nowDate = format.format(new Date());
		
		Date nowDate2 = format.parse(nowDate);
		Date birth = format.parse(userVO.getBirth());
		
		long diff = nowDate2.getTime() - birth.getTime();
		long diffDays = diff / (24*60*60*1000);
		long diffYears = (diffDays / 365);
		
		if(diffYears < 7) {
			return new ResultVO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", "나이가 7세미만입니다.");
		}
		
		// emailCheck
		String email = userVO.getEmail();
		int emailCheck = userDAO.countByEmail(email);
		if(emailCheck>0) {
			return new ResultVO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", " 가입된 이메일이 있습니다.");
		}
		userVO.setCreatedAt(new Date());
		userDAO.save(userVO);
		
		Long userId = userVO.getId();
		userVO = userDAO.findUserById(userId);
		
		return new ResultVO(HttpStatus.OK.value(), "Success", userVO);
	}
	
	public int findUserByEmail(String email) {
		return userDAO.countByEmail(email);
	}

	public Token login(User userVO) {
		
		Long userId = userVO.getId();
		logger.info("userId : "+ userId);
		
		String token = RandomToken.makeToken().toString();
		
		Token tokenVO = new Token();
		tokenVO.setToken(token);
		tokenVO.setUser(userVO);
		tokenVO.setCreatedAt(new Date());
		
		tokenDAO.save(tokenVO);
		
		return tokenDAO.findByToken(token);
		
	}

	public User findUserByEmailAndPassword(String email, String password) {
		return userDAO.findUserByEmailAndPassword(email, password);
	}
}