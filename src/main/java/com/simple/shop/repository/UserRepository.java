package com.simple.shop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simple.shop.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	User findUserById(Long id);

	int countByEmail(String email);

	User findUserByEmailAndPassword(String email, String password);

}
